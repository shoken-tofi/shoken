package com.bsuir.shoken.payment;

import com.bsuir.shoken.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "bills")
@SequenceGenerator(name = "entity_generator", sequenceName = "bills_seq", allocationSize = 1, initialValue = 2)
class Bill extends BaseEntity {

    @Column(nullable = false)
    private BigInteger value;

    @Column(nullable = false, precision = 2, scale = 12)
    private BigDecimal amount;
}
