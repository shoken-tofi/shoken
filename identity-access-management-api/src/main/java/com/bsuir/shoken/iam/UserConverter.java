package com.bsuir.shoken.iam;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
class UserConverter {

    UserDto convert(User user) {

        if (user == null) {
            return null;
        }

        final UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(convert(user.getRole()));

        return dto;
    }

    List<UserDto> convert(List<User> users) {

        if (users == null) {
            return Collections.emptyList();
        }

        return users.stream().map(this::convert).collect(Collectors.toList());
    }

    private RoleDto convert(Role role) {

        if (role == null) {
            return null;
        }

        final RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());

        return dto;
    }
}
