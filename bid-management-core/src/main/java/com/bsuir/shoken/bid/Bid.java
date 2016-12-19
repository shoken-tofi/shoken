package com.bsuir.shoken.bid;

import com.bsuir.shoken.BaseEntity;
import com.bsuir.shoken.BaseImage;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "bids")
@SequenceGenerator(name = "entity_generator", sequenceName = "bids_seq", allocationSize = 1)
public class Bid extends BaseEntity {

    @Column(name = "seller_id", nullable = false, updatable = false)
    private final Long sellerId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(nullable = false)
    private final String title;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "featured_image_id", nullable = false)
    private final Image featuredImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private final Type type;

    @Column(nullable = false)
    private final Integer quantity;

    @Column(nullable = false, length = 2000)
    private final String description;

    @Column(name = "start_price", nullable = false, precision = 2, scale = 12)
    private final BigDecimal startPrice;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "expiration_date", nullable = false)
    private final LocalDateTime expirationDate;

    @Column(name = "payment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private final PaymentType paymentType;

    @Column(length = 2000)
    private String comment;

    @OneToMany
    @JoinColumn(name = "bid_id", nullable = false, insertable = false, updatable = false)
    List<Bet> bets;

    enum Status {

        ACTIVE, DELETED, CANCELED, IN_PAYMENT
    }

    enum Type {

        SHARE, BOND, BILL
    }

    enum PaymentType {

        PAYPAL("PayPal");

        private String name;

        PaymentType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)

    @Entity
    @Table(name = "bid_images")
    @SequenceGenerator(name = "entity_generator", sequenceName = "bid_images_seq", allocationSize = 1)
    static class Image extends BaseImage {

        Image(String name) {
            super(name);
        }

        Image(String path, String name, Extension extension) {
            super(name);
            setPath(path);
            setExtension(extension);
        }
    }
}
