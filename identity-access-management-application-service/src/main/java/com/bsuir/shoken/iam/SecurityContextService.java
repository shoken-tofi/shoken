package com.bsuir.shoken.iam;

public interface SecurityContextService {

    boolean isAuthenticated();

    String getAuthentication();

    void setAuthentication(final User user);
}
