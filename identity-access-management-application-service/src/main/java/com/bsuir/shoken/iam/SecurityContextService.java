package com.bsuir.shoken.iam;

import java.util.List;

public interface SecurityContextService {

    boolean isAuthenticated();

    boolean isAdmin();

    List<String> getRoles();

    String getUsername();

    void setAuthentication(final User user);
}
