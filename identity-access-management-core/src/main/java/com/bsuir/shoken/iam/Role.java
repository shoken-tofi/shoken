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
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(name = "uk_roles_name", columnNames = "name"))
@SequenceGenerator(name = "entity_generator", sequenceName = "roles_seq")
class Role extends BaseEntity {

    @Column(nullable = false, unique = true)
    private final String name;
}
