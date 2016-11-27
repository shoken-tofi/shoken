package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class StepFactory {

    private static final BigDecimal DEFAULT_STEP_FACTOR = BigDecimal.valueOf(0.05);

    static BigDecimal define(final BigDecimal value) {
        return value.multiply(DEFAULT_STEP_FACTOR, new MathContext(2));
    }
}
