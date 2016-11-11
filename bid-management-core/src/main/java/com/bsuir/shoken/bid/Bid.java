package com.bsuir.shoken.bid;

import com.bsuir.shoken.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "bids")
@SequenceGenerator(name = "entity_generator", sequenceName = "bids_seq")
class Bid extends BaseEntity {

    @Column(name = "seller_id", nullable = false, updatable = false)
    private final Long sellerId;

    @Enumerated(EnumType.STRING)
    private final Status status;

    @Column(name = "category_id", nullable = false)
    private final Long categoryId;

    @Column(nullable = false)
    private final String title;

    @Enumerated(EnumType.STRING)
    private final Type type;

    @Column(nullable = false)
    private final Integer quantity;

    @Column(nullable = false, length = 1000)
    private final String description;

    @Column(nullable = false)
    private final BigDecimal startPrice;

    @Column(nullable = false)
    private final BigDecimal step;

    @Column(name = "creation_date", nullable = false)
    private final LocalDateTime creationDate;

    @Column(name = "expiration_date", nullable = false)
    private final LocalDateTime expirationDate;

    @Column(name = "payment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private final PaymentType paymentType;

    @Column(nullable = false)
    private String comment;

    enum Status {

        NEW, IN_PROGRESS, DELETED
    }

    enum Type {

        SHARE, BOND, BILL
    }

    enum PaymentType {

        PAY_PAL
    }
}
