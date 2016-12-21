package com.bsuir.shoken.payment;

import com.bsuir.shoken.NoSuchEntityException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
@Transactional
class DealService {

    private final DealRepository dealRepository;

    private final VendorRepository vendorRepository;

    private final VendeeRepository vendeeRepository;

    private final BillRepository billRepository;

    private final TaskScheduler taskScheduler;

    Bill createRandomBill() {
        return new Bill(BigInteger.probablePrime(20, new SecureRandom()),
                BigDecimal.valueOf(new SecureRandom().nextDouble()));
    }

    Deal findByBidId(final Long bidId) {
        return dealRepository.findOneByBidId(bidId);
    }

    Deal pay(final Long bidId) {

        final Deal deal = dealRepository.findOneByBidId(bidId);
        deal.setStatus(Deal.Status.PAID);

        final Deal dealFromDatabase = dealRepository.save(deal);
        final BigDecimal amount = deal.getAmount();

        final Vendee vendee = vendeeRepository.findOne(deal.getVendeeId());
        final Bill vendeeBill = vendee.getBill();
        final BigDecimal newVendeeAmount = vendeeBill.getAmount().subtract(amount);
        vendeeBill.setAmount(newVendeeAmount);
        billRepository.save(vendeeBill);

        final Bill companyBill = billRepository.findOne(1L);
        final BigDecimal newCompanyBill = companyBill.getAmount().add(amount);
        companyBill.setAmount(newCompanyBill);
        billRepository.save(companyBill);

        final Date expirationDate = Date.from(LocalDateTime.now().plusHours(24).atZone(ZoneId.systemDefault()).toInstant());
        taskScheduler.schedule(() -> expire(dealFromDatabase, false), expirationDate);

        return dealFromDatabase;
    }

    Deal transfer(final Long bidId) {

        final Deal deal = dealRepository.findOneByBidId(bidId);
        deal.setStatus(Deal.Status.TRANSFERRED);

        final Deal dealFromDatabase = dealRepository.save(deal);

        final Date expirationDate = Date.from(LocalDateTime.now().plusHours(24).atZone(ZoneId.systemDefault()).toInstant());
        taskScheduler.schedule(() -> expire(dealFromDatabase, true), expirationDate);

        return dealFromDatabase;
    }

    Deal confirm(final Long bidId) {

        final Deal deal = dealRepository.findOneByBidId(bidId);
        deal.setStatus(Deal.Status.CONFIRMED);

        final Deal dealFromDatabase = dealRepository.save(deal);
        final BigDecimal amount = deal.getAmount();

        final Vendor vendor = vendorRepository.findOne(deal.getVendorId());
        final Bill vendorBill = vendor.getBill();
        final BigDecimal newVendorAmount = vendorBill.getAmount().add(amount);
        vendorBill.setAmount(newVendorAmount);
        billRepository.save(vendorBill);

        final Bill companyBill = billRepository.findOne(1L);
        final BigDecimal newCompanyBill = companyBill.getAmount().subtract(amount);
        companyBill.setAmount(newCompanyBill);
        billRepository.save(companyBill);

        return dealFromDatabase;
    }

    Deal create(final String seller, final String investor, final Long bidId, final BigDecimal amount) throws NoSuchEntityException {

        final Deal deal = dealRepository.save(
                new Deal(vendorRepository.findOneByName(seller)
                        .orElseThrow(() -> new NoSuchEntityException("Vendor with such name = " + seller + " doesn't exists.")).getId(),
                        vendeeRepository.findOneByName(investor)
                                .orElseThrow(() -> new NoSuchEntityException("Vendee with such name = " + investor + " doesn't exists.")).getId(),
                        bidId, amount));

        final Date expirationDate = Date.from(LocalDateTime.now().plusHours(24).atZone(ZoneId.systemDefault()).toInstant());
        taskScheduler.schedule(() -> expire(deal, true), expirationDate);

        return deal;
    }

    @Async
    private void expire(final Deal deal, final boolean vendeeFault) {

        if (!vendeeFault) {
            final BigDecimal amount = deal.getAmount();

            final Vendee vendee = vendeeRepository.findOne(deal.getVendeeId());
            final Bill vendeeBill = vendee.getBill();
            final BigDecimal newVendeeAmount = vendeeBill.getAmount().add(amount);
            vendeeBill.setAmount(newVendeeAmount);
            billRepository.save(vendeeBill);

            final Bill companyBill = billRepository.findOne(1L);
            final BigDecimal newCompanyBill = companyBill.getAmount().subtract(amount);
            companyBill.setAmount(newCompanyBill);
            billRepository.save(companyBill);
        }

        deal.setStatus(Deal.Status.CANCELED);
        dealRepository.save(deal);
    }
}
