package com.bsuir.shoken.iam;

import com.bsuir.shoken.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "users")
@SequenceGenerator(name = "entity_generator", sequenceName = "users_seq")
class User extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private final String login;

    @Column(nullable = false, length = 60)
    private final String password;

    @Column(nullable = false, unique = true, length = 100)
    private final String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role = new Role(Role.RoleName.USER);

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate = LocalDate.now();
}
