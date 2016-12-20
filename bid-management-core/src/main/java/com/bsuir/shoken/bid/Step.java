package com.bsuir.shoken.bid;

import com.bsuir.shoken.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "steps")
@SequenceGenerator(name = "entity_generator", sequenceName = "steps_seq", allocationSize = 1)
class Step extends BaseEntity {

    @Column(nullable = false, precision = 2, scale = 12)
    private final BigDecimal low;

    @Column(nullable = false, precision = 2, scale = 12)
    private final BigDecimal high;

    @Column(nullable = false, precision = 2, scale = 12)
    private final BigDecimal value;
}
