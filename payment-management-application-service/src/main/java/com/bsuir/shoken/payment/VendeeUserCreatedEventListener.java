package com.bsuir.shoken.payment;

import com.bsuir.shoken.iam.UserCreatedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Component
class VendeeUserCreatedEventListener implements ApplicationListener<UserCreatedEvent> {

    private final VendeeService vendeeService;

    private final DealService dealService;

    @Override
    public void onApplicationEvent(final UserCreatedEvent event) {

        if (event == null) {
            return;
        }

        final Vendee vendee = new Vendee(event.getUsername(), dealService.createRandomBill());
        vendeeService.create(vendee);
    }
}
