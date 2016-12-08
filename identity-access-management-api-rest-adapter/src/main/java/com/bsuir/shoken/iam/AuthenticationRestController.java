package com.bsuir.shoken.iam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AuthenticationRestController extends AuthenticationController {

    @Autowired
    AuthenticationRestController(UserConverter userConverter, UserService userService,
                                 SecurityContextService securityContextService) {
        super(userConverter, userService, securityContextService);
    }
}
