package com.bsuir.shoken.iam;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
class UserConverter {

    User toEntity(final RegisterDto dto) {

        if (dto == null) {
            return null;
        }

        return new User(dto.getUsername(), dto.getPassword(), dto.getEmail());
    }

    UserDto toDto(final User user) {

        if (user == null) {
            return null;
        }

        final UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().toString());

        return dto;
    }

    List<UserDto> toDtos(final List<User> users) {

        if (users == null) {
            return Collections.emptyList();
        }

        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
