package com.bsuir.shoken.iam;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class LoginDto {

    @NotNull
    private String login;

    @NotNull
    private String password;
}
