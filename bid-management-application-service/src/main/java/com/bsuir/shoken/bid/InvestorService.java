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
class InvestorService {

    private final InvestorRepository investorRepository;

    @Transactional(readOnly = true)
    Investor findById(final Long id) {
        return investorRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    Investor findByName(final String name) throws NoSuchEntityException {
        return investorRepository.findOneByName(name)
                .orElseThrow(() -> new NoSuchEntityException("Investor with such name = " + name + " doesn't exists."));
    }

    Investor create(final Investor investor) {
        return investorRepository.save(investor);
    }
}
