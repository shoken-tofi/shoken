package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@Component
class BidConverter {

    private final SellerService sellerService;

    private final SellerConverter sellerConverter;

    List<BidDto> convertToDtos(final List<Bid> bids) {

        if (bids == null) {
            return Collections.emptyList();
        }

        return bids.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    BidDetailsDto convertToDetailsDto(final Bid bid) {

        if (bid == null) {
            return null;
        }

        final BidDetailsDto dto = new BidDetailsDto();
        //TODO: mapping fields

        return dto;
    }

    private BidDto convertToDto(final Bid bid) {

        if (bid == null) {
            return null;
        }

        final BidDto dto = new BidDto();
        dto.setId(bid.getId());
        dto.setTitle(bid.getTitle());
        dto.setType(bid.getType());
        dto.setQuantity(bid.getQuantity());
        //TODO: if no bets - startPrice; otherwise - the highest bet price;
        dto.setPrice(bid.getStartPrice());

        final Bid.Image image = bid.getFeaturedImage();
        //TODO: create image url with host, path, name and extension
        dto.setImageUrl("" + image.getPath() + image.getName() + image.getExtension().toString().toLowerCase());

        dto.setTimeLeft(convertToTimeLeftDto(bid.getExpirationDate()));
        dto.setPaymentType(bid.getPaymentType());
        dto.setSeller(convertToSellerDto(bid.getSellerId()));

        return dto;
    }

    private BidDto.TimeLeftDto convertToTimeLeftDto(final LocalDateTime expirationDate) {

        LocalDateTime now = LocalDateTime.now();
        final long hours = HOURS.between(now, expirationDate);

        now = now.plusHours(hours);
        final long minutes = MINUTES.between(now, expirationDate);

        now = now.plusMinutes(minutes);
        final long seconds = SECONDS.between(now, expirationDate);

        return new BidDto.TimeLeftDto(hours, minutes, seconds);
    }

    private SellerDto convertToSellerDto(final Long sellerId) {

        final Seller seller = sellerService.findOne(sellerId);

        return sellerConverter.convert(seller);
    }
}
