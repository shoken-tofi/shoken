package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

    private final BetService betService;

    private final BetConverter betConverter;

    Bid toEntity(final BidCreateDto dto) {

        if (dto == null) {
            return null;
        }

        final Long sellerId = 1L; // TODO: get authenticated user
        final ImageDto image = dto.getImage();
        final PriceDto price = dto.getPrice();
        final Bid bid = new Bid(sellerId, dto.getTitle(), new Bid.Image("/bid_images/",
                image.getName(), Bid.Image.Extension.valueOf(image.getExtension())), Bid.Type.valueOf(dto.getType()),
                dto.getQuantity(), dto.getDescription(), price.getValue(), price.getStep(), LocalDateTime.now(),
                dto.getExpirationDate(), Bid.PaymentType.valueOf(dto.getPaymentType()));
        bid.setComment(dto.getComment());

        return bid;
    }

    BidFindOneDto toFindOneDto(final Bid bid) {

        if (bid == null) {
            return null;
        }

        final BidFindOneDto dto = new BidFindOneDto();
        dto.setId(bid.getId());
        dto.setTitle(bid.getTitle());
        dto.setType(bid.getType().toString().toLowerCase());
        dto.setQuantity(bid.getQuantity());
        dto.setDescription(bid.getDescription());
        dto.setPrice(new PriceDto(toPrice(bid.getStartPrice(), bid.getId()), bid.getStep()));
        dto.setImageUrl(toImageUrl(bid.getFeaturedImage()));
        dto.setTimeLeft(toTimeLeftDto(bid.getExpirationDate()));
        dto.setPaymentType(bid.getPaymentType().toString().toLowerCase());
        dto.setSeller(toSellerFindAllDto(bid.getSellerId()));
        dto.setComment(bid.getComment());
        dto.setBets(toBetFindAllDtos(bid.getId()));

        return dto;
    }

    List<BidFindAllDto> toFindAllDtos(final List<Bid> bids) {

        if (bids == null) {
            return Collections.emptyList();
        }

        return bids.stream().map(this::toFindAllDto).collect(Collectors.toList());
    }

    private BidFindAllDto toFindAllDto(final Bid bid) {

        if (bid == null) {
            return null;
        }

        final BidFindAllDto dto = new BidFindAllDto();
        dto.setId(bid.getId());
        dto.setTitle(bid.getTitle());
        dto.setType(bid.getType().toString().toLowerCase());
        dto.setQuantity(bid.getQuantity());
        dto.setPrice(new PriceDto(toPrice(bid.getStartPrice(), bid.getId()), bid.getStep()));
        dto.setImageUrl(toImageUrl(bid.getFeaturedImage()));
        dto.setTimeLeft(toTimeLeftDto(bid.getExpirationDate()));
        dto.setPaymentType(bid.getPaymentType().toString().toLowerCase());
        dto.setSeller(toSellerFindAllDto(bid.getSellerId()));

        return dto;
    }

    private BigDecimal toPrice(final BigDecimal startPrice, final Long bidId) {

        final Bet bet = betService.findFirstByBidId(bidId);
        if (bet == null) {
            return startPrice;
        }

        return bet.getValue();
    }

    private String toImageUrl(final Bid.Image image) {
        return "" + image.getPath() + image.getName() + "." + image.getExtension().toString().toLowerCase();
    }

    private TimeLeftDto toTimeLeftDto(final LocalDateTime expirationDate) {

        LocalDateTime now = LocalDateTime.now();
        final Long hours = HOURS.between(now, expirationDate);

        now = now.plusHours(hours);
        final Long minutes = MINUTES.between(now, expirationDate);

        now = now.plusMinutes(minutes);
        final Long seconds = SECONDS.between(now, expirationDate);

        return new TimeLeftDto(hours, minutes, seconds);
    }

    private SellerFindAllDto toSellerFindAllDto(final Long id) {

        final Seller seller = sellerService.findOne(id);

        return sellerConverter.toFindAllDto(seller);
    }

    private List<BetFindAllDto> toBetFindAllDtos(final Long bidId) {

        final List<Bet> bets = betService.findByBidId(bidId);

        return betConverter.toFindAllDtos(bets);
    }
}
