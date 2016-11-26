package com.bsuir.shoken.iam;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class SpringAuthenticatedUser extends User implements AuthenticatedUser {

    SpringAuthenticatedUser(final String username, final String password, final Collection<String> authorities) {
        super(username, password, authorities.stream()
                .map(s -> new SimpleGrantedAuthority("ROLE_" + s))
                .collect(Collectors.toList()));
    }

    @Override
    public List<String> getRoles() {
        return getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                .collect(Collectors.toList());
    }
}
