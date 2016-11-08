package com.bsuir.shoken.iam;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq")
    private Long id;

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
