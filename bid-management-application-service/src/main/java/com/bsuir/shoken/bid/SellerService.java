package com.bsuir.shoken.bid;

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
    Seller findOne(final Long id) {
        return sellerRepository.findOne(id);
    }

    Seller create(final Seller seller) {
        return sellerRepository.save(seller);
    }
}
