package com.bsuir.shoken.bid;

import com.bsuir.shoken.iam.UserCreatedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Component
class InvestorUserCreatedEventListener implements ApplicationListener<UserCreatedEvent> {

    private final InvestorService investorService;

    @Override
    public void onApplicationEvent(final UserCreatedEvent event) {

        if (event == null) {
            return;
        }

        final Investor investor = new Investor(event.getId(), event.getUsername());
        investorService.create(investor);
    }
}
