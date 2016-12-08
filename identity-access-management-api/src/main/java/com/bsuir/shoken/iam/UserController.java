package com.bsuir.shoken.iam;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/users")
abstract class UserController {

    private final static String PAGE = "1";

    private final static String SIZE = "10";

    private final UserService userService;

    private final UserConverter userConverter;

    @RequestMapping(method = RequestMethod.GET)
    UsersDto get(@RequestParam(required = false, defaultValue = PAGE) int page,
                 @RequestParam(required = false, defaultValue = SIZE) int size) {

        final Pageable pageRequest = new PageRequest(--page, size);
        final Page<User> users = userService.findAll(pageRequest);

        return new UsersDto(userConverter.toDTOs(users.getContent()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    UserDto get(@PathVariable Long id) {

        final User user = userService.findById(id);

        return userConverter.toDto(user);
    }
}
