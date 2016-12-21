package com.bsuir.shoken.iam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserRestController extends UserController {

    @Autowired
    UserRestController(UserService userService, UserConverter userConverter,
                       SecurityContextService securityContextService) {
        super(userService, userConverter, securityContextService);
    }
}
