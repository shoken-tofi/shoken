package com.bsuir.shoken.bid;

import com.bsuir.shoken.NoSuchEntityException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
@Transactional
class SellerService {

    private final SellerRepository sellerRepository;

    @Transactional(readOnly = true)
    Seller findById(final Long id) {
        return sellerRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    Seller findByName(final String name) throws NoSuchEntityException {
        return sellerRepository.findOneByName(name)
                .orElseThrow(() -> new NoSuchEntityException("Seller with such name = " + name + " doesn't exists."));
    }

    Seller create(final Seller seller) {
        return sellerRepository.save(seller);
    }
}
