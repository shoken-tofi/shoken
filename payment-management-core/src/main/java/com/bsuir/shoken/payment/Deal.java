package com.bsuir.shoken.payment;

import com.bsuir.shoken.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "deals")
@SequenceGenerator(name = "entity_generator", sequenceName = "deals_seq", allocationSize = 1)
class Deal extends BaseEntity {

    @Column(name = "vendor_id", nullable = false, updatable = false)
    private final Long vendorId;

    @Column(name = "vendee_id", nullable = false, updatable = false)
    private final Long vendeeId;

    @Column(name = "bid_id", nullable = false, updatable = false)
    private final Long bidId;

    @Column(nullable = false, precision = 2, scale = 12)
    private final BigDecimal amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    enum Status {

        NEW, PAID, TRANSFERRED, CONFIRMED, CANCELED
    }
}
