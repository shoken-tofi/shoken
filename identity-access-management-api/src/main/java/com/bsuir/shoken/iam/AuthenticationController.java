package com.bsuir.shoken.iam;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/")
@Validated
abstract class AuthenticationController {

    private final UserConverter userConverter;

    private final UserService userService;

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    UserDto register(@RequestBody RegisterDto dto) {

        final User user = userConverter.toEntity(dto);
        final User userFromDatabase = userService.create(user);

        return userConverter.toDto(userFromDatabase);
    }
}
