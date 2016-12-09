package com.bsuir.shoken.bid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))

@Service
@Transactional
public class BidService {

    private final BidRepository bidRepository;

    @Transactional(readOnly = true)
    Page<Bid> findAll(final Pageable pageable) {

        final Bid bid = new Bid();

        final Example<Bid> example = Example.of(bid);

        return bidRepository.findAll(example, pageable);
    }

    @Transactional(readOnly = true)
    Bid findOne(final Long id) throws Exception {

        final Optional<Bid> bid = bidRepository.findOneById(id);

        return bid.orElseThrow(Exception::new);
    }

    public Bid create(final Bid bid) {
        return bidRepository.save(bid);
    }

    void delete(final Long id) {
        bidRepository.delete(id);
    }
}
