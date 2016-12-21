package com.bsuir.shoken.bid;

import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

public class BidFinishedEvent extends ApplicationEvent {

    private Long id;

    private Long sellerId;

    private Long investorId;

    private BigDecimal totalPrice;

    public BidFinishedEvent(BidFinishedEventVO source) {
        super(source);

        final Bid bid = source.getBid();
        id = bid.getId();
        sellerId = bid.getSellerId();

        final Bet maxBet = source.getMaxBet();
        investorId = maxBet.getInvestorId();
        totalPrice = maxBet.getValue();
    }
}
