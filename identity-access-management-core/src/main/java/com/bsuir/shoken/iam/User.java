package com.bsuir.shoken.iam;

import com.bsuir.shoken.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "users")
@SequenceGenerator(name = "entity_generator", sequenceName = "users_seq", allocationSize = 1)
public class User extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private final String login;

    @Column(nullable = false, length = 60)
    private final String password;

    @Column(nullable = false, unique = true, length = 100)
    private final String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName role = RoleName.USER;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate = LocalDate.now();

    enum RoleName {

        ADMIN, USER
    }
}
