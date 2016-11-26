package com.bsuir.shoken.iam;

interface AuthenticationService {

    AuthenticatedUser loadUserByUsername(final String username);
}
