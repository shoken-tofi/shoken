package com.bsuir.shoken.iam;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
class SpringAuthenticationService implements UserDetailsService {

    private final UserService userService;

    @Override
    public SpringAuthenticatedUser loadUserByUsername(final String username) throws UsernameNotFoundException {

        final Optional<User> userFromDatabase = userService.findByLogin(username);

        return userFromDatabase.map(user -> new SpringAuthenticatedUser(user.getLogin(), user.getPassword(),
                Collections.singletonList(user.getRole().toString())))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    static class SpringAuthenticatedUser extends org.springframework.security.core.userdetails.User {

        SpringAuthenticatedUser(final String username, final String password, final Collection<String> authorities) {
            super(username, password, authorities.stream()
                    .map(s -> new SimpleGrantedAuthority("ROLE_" + s))
                    .collect(Collectors.toList()));
        }
    }
}
