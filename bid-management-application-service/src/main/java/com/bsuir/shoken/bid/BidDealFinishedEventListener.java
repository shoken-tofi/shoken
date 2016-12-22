package com.bsuir.shoken.bid;

import com.bsuir.shoken.NoSuchEntityException;
import com.bsuir.shoken.ValidationException;
import com.bsuir.shoken.payment.DealFinishedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Component
class BidDealFinishedEventListener implements ApplicationListener<DealFinishedEvent> {

    private final BidService bidService;

    @Override
    public void onApplicationEvent(DealFinishedEvent event) {

        if (event == null) {
            return;
        }

        try {
            final Bid bid = bidService.findOne(event.getBidId()).getBid();
            final Bid.Status status;


            final String dealStatus = event.getStatus();
            if (dealStatus.equals("CANCELED")) {
                status = Bid.Status.CANCELED;
            } else {
                status = Bid.Status.FINISHED;
            }

            bid.setStatus(status);
            bidService.create(bid);
        } catch (NoSuchEntityException | ValidationException e) {
            e.printStackTrace();
        }
    }
}
