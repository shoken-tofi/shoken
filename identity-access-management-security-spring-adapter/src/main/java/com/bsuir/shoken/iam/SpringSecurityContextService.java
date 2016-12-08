package com.bsuir.shoken.iam;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
class SpringSecurityContextService implements SecurityContextService {

    @Override
    public void setAuthentication(final User user) {

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getLogin(),
                user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole())));

        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
