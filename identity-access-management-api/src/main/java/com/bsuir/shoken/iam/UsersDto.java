package com.bsuir.shoken.iam;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class UsersDto {

    private List<UserDto> users;
}
