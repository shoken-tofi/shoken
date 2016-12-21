package com.bsuir.shoken.payment;

import com.bsuir.shoken.NoSuchEntityException;
import com.bsuir.shoken.bid.BidFinishedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Component
class DealBidFinishedEventListener implements ApplicationListener<BidFinishedEvent> {

    private final DealService dealService;

    @Override
    public void onApplicationEvent(BidFinishedEvent event) {

        if (event == null) {
            return;
        }

        try {
            dealService.create(event.getSeller(), event.getInvestor(), event.getId(), event.getTotalPrice());
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
        }
    }
}
