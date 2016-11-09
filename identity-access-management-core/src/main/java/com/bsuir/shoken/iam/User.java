package com.bsuir.shoken.iam;

import com.bsuir.shoken.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "users")
@SequenceGenerator(name = "entity_generator", sequenceName = "users_seq")
class User extends BaseEntity {

    @Column(nullable = false, unique = true, length = 32)
    private final String username;

    @Column(nullable = false, length = 60)
    private final String password;

    @Column(nullable = false, unique = true)
    private final String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private final Role role;
}
