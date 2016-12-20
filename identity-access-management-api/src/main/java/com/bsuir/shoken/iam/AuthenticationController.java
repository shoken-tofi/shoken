package com.bsuir.shoken.iam;

import com.bsuir.shoken.AlreadyExistingEntityException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/")
abstract class AuthenticationController {

    private final UserConverter userConverter;

    private final UserService userService;

    private final SecurityContextService securityContextService;
    
    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterDto dto) throws AlreadyExistingEntityException {

        final User user = userConverter.toEntity(dto);
        final User userFromDatabase = userService.create(user);

        securityContextService.setAuthentication(userFromDatabase);

        return userConverter.toDto(userFromDatabase);
    }
}
