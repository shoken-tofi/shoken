package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
@Transactional
class BidService {

    private final BidRepository bidRepository;

    @Transactional(readOnly = true)
    Page<Bid> findAll(Pageable pageable) {

        final Example<Bid> example = Example.of(new Bid(), ExampleMatcher.matching());

        return bidRepository.findAll(example, pageable);
    }

    @Transactional(readOnly = true)
    Bid findOne(Long id) {

        final Bid bid = bidRepository.findOne(id);
        if (bid == null) {
            return null;
        }

        switch (bid.getStatus()) {
            case CREATED:
                break;
            case DELETED:
                // TODO: get authenticated user -> forbid access or redirect to me/bids/deleted
                break;
            case CANCELED:
                // TODO: get authenticated user -> forbid access or redirect to me/bids/canceled
                break;
            default:
                break;
        }

        return bid;
    }

    Bid create(Bid bid) {
        return bidRepository.save(bid);
    }

    void delete(Long id) {

        // TODO: get authenticated user -> forbid access or perform action

        bidRepository.delete(id);
    }
}
