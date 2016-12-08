package com.bsuir.shoken;

import com.bsuir.shoken.iam.UserConverter;
import com.bsuir.shoken.iam.UserService;
import com.bsuir.shoken.iam.UserServiceGateway;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
class UserInitializer {

    private static final int USER_COUNT = 1000;

    private final UserServiceGateway userServiceGateway;

    private final UserConverter userConverter;

    private final UserService userService;

    void init() {

//        final List<RegisterDto> createDTOs = userServiceGateway.create(USER_COUNT);
//        final List<User> users = userConverter.toEntities(createDTOs);
//        users.forEach(userService::create);
    }
}
