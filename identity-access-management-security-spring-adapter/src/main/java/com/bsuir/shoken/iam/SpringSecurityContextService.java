package com.bsuir.shoken.iam;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpringSecurityContextService implements SecurityContextService {

    @Override
    public boolean isAuthenticated() {
        return !SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
    }

    @Override
    public boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public List<String> getRoles() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().map(authority -> authority.getAuthority().replace("ROLE_", "")).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public void setAuthentication(final User user) {

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getLogin(),
                user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole())));

        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
