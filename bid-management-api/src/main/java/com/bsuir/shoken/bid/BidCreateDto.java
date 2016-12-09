package com.bsuir.shoken.bid;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BidCreateDto {

    private Long sellerId;

    private String title;

    private String imageName;

    private String type;

    private Integer quantity;

    private String description;

    private BigDecimal startPrice;

    private LocalDate expirationDate;

    private String paymentType;

    private String comment;
}
