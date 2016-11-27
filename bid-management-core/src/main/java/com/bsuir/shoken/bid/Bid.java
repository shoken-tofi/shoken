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
@SequenceGenerator(name = "entity_generator", sequenceName = "bids_seq", allocationSize = 1)
class Bid extends BaseEntity {

    @Column(name = "seller_id", nullable = false, updatable = false)
    private final Long sellerId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(nullable = false)
    private final String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "featured_image_id", nullable = false)
    private final Image featuredImage;

    @Enumerated(EnumType.STRING)
    private final Type type;

    @Column(nullable = false)
    private final Integer quantity;

    @Column(nullable = false, length = 2000)
    private final String description;

    @Column(name = "start_price", nullable = false, precision = 2, scale = 12)
    private final BigDecimal startPrice;

    @Column(name = "creation_date", nullable = false)
    private final LocalDateTime creationDate;

    @Column(name = "expiration_date", nullable = false)
    private final LocalDateTime expirationDate;

    @Column(name = "payment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private final PaymentType paymentType;

    @Column(nullable = false, length = 2000)
    private String comment;

    enum Status {

        ACTIVE, DELETED, CANCELED
    }

    enum Type {

        SHARE, BOND, BILL
    }

    enum PaymentType {

        PAY_PAL
    }

    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)

    @Entity
    @Table(name = "bid_images")
    @SequenceGenerator(name = "entity_generator", sequenceName = "bid_images_seq", allocationSize = 1)
    static class Image extends BaseImage {

        Image(String path) {
            super(path);
        }

        Image(String path, String name, Extension extension) {
            super(path);
            setName(name);
            setExtension(extension);
        }
    }
}
