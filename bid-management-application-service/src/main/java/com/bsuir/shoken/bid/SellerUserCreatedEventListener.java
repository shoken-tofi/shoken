package com.bsuir.shoken.bid;

import com.bsuir.shoken.iam.UserCreatedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Component
class SellerUserCreatedEventListener implements ApplicationListener<UserCreatedEvent> {

    private final SellerService sellerService;

    @Override
    public void onApplicationEvent(final UserCreatedEvent event) {

        if (event == null) {
            return;
        }

        final Seller seller = new Seller(event.getUsername());
        sellerService.create(seller);
    }
}
