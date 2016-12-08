package com.bsuir.shoken.iam;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
public class RegisterDto {

    @Size(min = 6, max = 50)
    @Pattern(regexp = "^[A-Za-z0-9]+(?:[_.-][A-Za-z0-9]+)*$",
            message = "Username must contain only digits, lowercase and uppercase letters " +
                    "separated by underscore, dot or hyphen.")
    private String username;

    @Size(min = 6, max = 50)
    @Pattern.List({
            @Pattern(regexp = "(?=.*[0-9]).+", message = "Password must contain at least one digit."),
            @Pattern(regexp = "(?=.*[a-z]).+", message = "Password must contain at least one lowercase letter."),
            @Pattern(regexp = "(?=.*[A-Z]).+", message = "Password must contain at least one uppercase letter."),
            @Pattern(regexp = "(?=.*[!@#$%^&*+=?-_()/\".,<>~`;:]).+",
                    message = "Password must contain at least one special character."),
            @Pattern(regexp = "^(?=\\S+$).+$", message = "Password mustn't contain any whitespace character.")
    })
    private String password;

    @Size(max = 100)
    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
            "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
    private String email;
}
