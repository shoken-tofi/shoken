package com.bsuir.shoken.bid;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

@Getter
public class BidFinishedEvent extends ApplicationEvent {

    private Long id;

    private String seller;

    private String investor;

    private BigDecimal totalPrice;

    public BidFinishedEvent(BidFinishedEventVO source) {
        super(source);

        final Bid bid = source.getBid();
        id = bid.getId();
        seller = source.getSeller();

        final Bet maxBet = source.getMaxBet();
        investor = source.getInvestor();
        totalPrice = maxBet.getValue();
    }
}
