package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        return bidRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    Bid findOne(Long id) {
        return bidRepository.findOne(id);
    }
}
