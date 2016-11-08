package com.bsuir.shoken.iam;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
class UserDto {

    private Long id;

    private String username;

    private String email;

    private RoleDto role;
}
