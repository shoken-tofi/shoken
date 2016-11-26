package com.bsuir.shoken.iam;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
class UserDto {

    private Long id;

    private String login;

    private String email;

    private String role;
}
