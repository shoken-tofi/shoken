package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
@Transactional
class BidService {

    private final BidRepository bidRepository;

    @Transactional(readOnly = true)
    Page<Bid> findAll(final Pageable pageable) {

        final Example<Bid> example = Example.of(new Bid());

        return bidRepository.findAll(example, pageable);
    }

    @Transactional(readOnly = true)
    Bid findOne(final Long id) throws Exception {

        final Optional<Bid> bid = bidRepository.findOneById(id);

        return bid.orElseThrow(Exception::new);
    }

    Bid create(final Bid bid) {
        return bidRepository.save(bid);
    }

    void delete(final Long id) {
        bidRepository.delete(id);
    }
}
