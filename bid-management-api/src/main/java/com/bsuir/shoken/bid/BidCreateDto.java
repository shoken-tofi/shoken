package com.bsuir.shoken.bid;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BidCreateDto {

    private String title;

    private String type;

    private Integer quantity;

    private String description;

    private PriceDto price;

    private ImageDto image;

    private LocalDateTime expirationDate;

    private String paymentType;

    private String comment;
}
