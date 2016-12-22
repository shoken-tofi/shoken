package com.bsuir.shoken.payment;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DealFinishedEvent extends ApplicationEvent {

    private Long bidId;

    private String status;

    public DealFinishedEvent(final Deal source) {
        super(source);
        bidId = source.getBidId();
        status = source.getStatus().toString();
    }
}
