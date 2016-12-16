package com.bsuir.shoken.bid;

import com.bsuir.shoken.BaseImage;
import com.bsuir.shoken.ShokenConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.*;
import static org.apache.commons.io.FilenameUtils.getBaseName;
import static org.apache.commons.io.FilenameUtils.getExtension;

@RequiredArgsConstructor

@Component
public class BidConverter {

    private final static String IMAGE_PATH = "/images/bids/";

    private final SellerService sellerService;

    private final SellerConverter sellerConverter;

    private final BetService betService;

    private final BetConverter betConverter;

    private final ShokenConfigurationProperties configurationProperties;

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
        dto.setPrice(toPriceDto(bid.getId(), bid.getStartPrice()));
        dto.setImageUrl(toImageUrl(bid.getFeaturedImage()));
        dto.setTimeLeft(toTimeLeftDto(bid.getExpirationDate()));
        dto.setPaymentType(bid.getPaymentType().toString().toLowerCase());
        dto.setSeller(toSellerFindAllDto(bid.getSellerId()));
        dto.setComment(bid.getComment());
        dto.setBets(toBetFindAllDTOs(bid.getId()));

        return dto;
    }

    List<BidFindAllDto> toFindAllDTOs(final List<Bid> bids) {

        if (bids == null) {
            return Collections.emptyList();
        }

        return bids.stream().map(this::toFindAllDto).collect(Collectors.toList());
    }

    BidFindAllDto toFindAllDto(final Bid bid) {

        if (bid == null) {
            return null;
        }

        final BidFindAllDto dto = new BidFindAllDto();
        dto.setId(bid.getId());
        dto.setTitle(bid.getTitle());
        dto.setType(bid.getType().toString().toLowerCase());
        dto.setQuantity(bid.getQuantity());
        dto.setPrice(toPriceDto(bid.getId(), bid.getStartPrice()));
        dto.setImageUrl(toImageUrl(bid.getFeaturedImage()));
        dto.setTimeLeft(toTimeLeftDto(bid.getExpirationDate()));
        dto.setPaymentType(bid.getPaymentType().toString().toLowerCase());
        dto.setSeller(toSellerFindAllDto(bid.getSellerId()));

        return dto;
    }

    private PriceDto toPriceDto(final Long bidId, final BigDecimal startPrice) {

        final Optional<Bet> bet = betService.findMaxByBidId(bidId);
        final BigDecimal price = bet.map(Bet::getValue).orElse(startPrice);

        final BigDecimal step = BigDecimal.ONE; //TODO: get from database

        return new PriceDto(price, step);
    }

    private String toImageUrl(final Bid.Image image) {

        final String path = image.getPath();

        return path.startsWith("http") ? path : configurationProperties.getImageServer().getName() + image.getPath()
                + image.getName() + "." + image.getExtension().toString().toLowerCase();
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

    private List<BetFindAllDto> toBetFindAllDTOs(final Long bidId) {

        final Pageable pageRequest = new PageRequest(Integer.valueOf(BetController.PAGE) - 1,
                Integer.valueOf(BetController.SIZE));
        final Page<Bet> bets = betService.findByBidId(bidId, pageRequest);

        return betConverter.toFindAllDTOs(bets.getContent());
    }

    Bid toEntity(final BidCreateDto dto) {

        if (dto == null) {
            return null;
        }

        final Bid.Image featureImage;
        final String featureImageName = dto.getImageName();
        if (featureImageName.startsWith("http")) {
            featureImage = new Bid.Image(featureImageName);
        } else {
            featureImage = new Bid.Image(IMAGE_PATH, getBaseName(featureImageName),
                    BaseImage.Extension.valueOf(getExtension(featureImageName).toUpperCase()));
        }

        final LocalDateTime now = LocalDateTime.now();

        final Bid bid = new Bid(dto.getSellerId(), dto.getTitle(), featureImage,
                Bid.Type.valueOf(dto.getType().toUpperCase()), dto.getQuantity(), dto.getDescription(),
                dto.getStartPrice(), LocalDateTime.of(dto.getExpirationDate(), now.toLocalTime()),
                Bid.PaymentType.valueOf(dto.getPaymentType().toUpperCase()));
        bid.setCreationDate(now);
        bid.setComment(dto.getComment());

        return bid;
    }

    public List<Bid> toEntities(final List<BidCreateDto> dtoList) {

        if (dtoList == null) {
            return Collections.emptyList();
        }

        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
