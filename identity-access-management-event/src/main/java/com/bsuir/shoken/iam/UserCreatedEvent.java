package com.bsuir.shoken.iam;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserCreatedEvent extends ApplicationEvent {

    private String username;

    public UserCreatedEvent(final User source) {
        super(source);
        this.username = source.getLogin();
    }
}
