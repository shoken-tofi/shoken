package com.bsuir.shoken.bid;

import com.bsuir.shoken.NoSuchEntityException;
import com.bsuir.shoken.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))

@Service
@Transactional
public class BidService {

    private final BidRepository bidRepository;

    private final BetRepository betRepository;

    private final TaskScheduler taskScheduler;

    private final ApplicationEventPublisher publisher;

    @Transactional(readOnly = true)
    Page<Bid> findAll(final Pageable pageable) {

        //TODO: search by criteria

        return bidRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    Bid findOne(final Long id) throws Exception {

        final Optional<Bid> bid = bidRepository.findOneById(id);

        return bid.orElseThrow(() -> new NoSuchEntityException("Bid with such id = " + id + " already exists."));
    }

    public Bid create(final Bid bid) {

        final Bid bidFromDatabase = bidRepository.save(bid);

        final Date startDate = Date.from(bid.getExpirationDate().atZone(ZoneId.systemDefault()).toInstant());
        final ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(() -> expire(bidFromDatabase), startDate);

        return bidFromDatabase;
    }

    @Async
    private void expire(final Bid bid) {

        final Bid.Status status;

        final Optional<Bet> maxBet = betRepository.findFirstByBidIdOrderByValueDesc(bid.getId());
        if (!maxBet.isPresent()) {
            status = Bid.Status.CANCELED;
        } else {
            status = Bid.Status.IN_PAYMENT;
        }

        bid.setStatus(status);
        bidRepository.save(bid);
    }

    void delete(final Long id) throws NoSuchEntityException, ValidationException {

        final Bid bid = bidRepository.findOneById(id)
                .orElseThrow(() -> new NoSuchEntityException("Bid with such id = " + id + " already exists."));

        final Bid.Status deleted = Bid.Status.DELETED;
        if (bid.getStatus() == deleted) {
            throw new ValidationException("Bid with id = " + id + " already deleted.");
        }

        bid.setStatus(deleted);
        bidRepository.save(bid);
    }
}
