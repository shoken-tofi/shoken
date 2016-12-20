package com.bsuir.shoken.bid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))

@Service
@Transactional
class StepService {

    private final StepRepository stepRepository;

    @Transactional(readOnly = true)
    Step findByPredicate(final BigDecimal price) {
        return stepRepository.findByPredicate(price).orElse(new Step(BigDecimal.ZERO, price, BigDecimal.ONE));
    }
}
