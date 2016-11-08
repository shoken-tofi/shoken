package com.bsuir.shoken.iam;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")

@Entity
@Table(name = "roles")
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name = "role_generator", sequenceName = "role_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    private final String name;
}
