package com.bsuir.shoken;

import com.bsuir.shoken.iam.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
class UserInitializer {

    private static final Logger LOGGER = getLogger(UserInitializer.class);

    private static final int USER_COUNT = 100;

    private final UserServiceGateway userServiceGateway;

    private final UserConverter userConverter;

    private final UserService userService;

    void init() {

        final List<RegisterDto> createDTOs = userServiceGateway.create(USER_COUNT);
        final List<User> users = userConverter.toEntities(createDTOs);
        users.forEach((userToCreate) -> {
            try {
                userService.create(userToCreate);
            } catch (AlreadyExistingEntityException e) {
                LOGGER.error("Error occurred while saving new User", e);
            }
        });
    }
}
