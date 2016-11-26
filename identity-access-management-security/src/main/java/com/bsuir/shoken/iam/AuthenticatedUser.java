package com.bsuir.shoken.iam;

import java.util.List;

interface AuthenticatedUser {

    String getUsername();

    String getPassword();

    List<String> getRoles();
}
