package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
@Transactional
class InvestorService {

    private final InvestorRepository investorRepository;

    @Transactional(readOnly = true)
    Investor findOne(Long id) {
        return investorRepository.findOne(id);
    }
}
