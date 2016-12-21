package com.bsuir.shoken.bid;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
class SearchCriteriaConverter {

    SearchCriteria toEntity(final SearchCriteriaDto dto) {

        if (dto == null) {
            return null;
        }

        long minStartDateSecondsGone = 0;
        final BigDecimal minStartDateHoursGone = dto.getMinStartDateHoursGone();
        if (minStartDateHoursGone != null) {
            minStartDateSecondsGone = minStartDateHoursGone
                    .multiply(BigDecimal.valueOf(ChronoUnit.HOURS.getDuration().getSeconds()))
                    .longValue();
        }

        long maxStartDateSecondsGone = 0;
        final BigDecimal maxStartDateHoursGone = dto.getMaxStartDateHoursGone();
        if (maxStartDateHoursGone != null) {
            maxStartDateSecondsGone = maxStartDateHoursGone
                    .multiply(BigDecimal.valueOf(ChronoUnit.HOURS.getDuration().getSeconds()))
                    .longValue();
        }

        long minExpirationDateSecondsLeft = 0;
        final BigDecimal minExpirationDateHoursLeft = dto.getMinExpirationDateHoursLeft();
        if (minExpirationDateHoursLeft != null) {
            minExpirationDateSecondsLeft = minExpirationDateHoursLeft
                    .multiply(BigDecimal.valueOf(ChronoUnit.HOURS.getDuration().getSeconds()))
                    .longValue();
        }

        long maxExpirationDateSecondsLeft = 0;
        final BigDecimal maxExpirationDateHoursLeft = dto.getMaxExpirationDateHoursLeft();
        if (maxExpirationDateHoursLeft != null) {
            maxExpirationDateSecondsLeft = maxExpirationDateHoursLeft
                    .multiply(BigDecimal.valueOf(ChronoUnit.HOURS.getDuration().getSeconds()))
                    .longValue();
        }

        final List<String> bidTypes = dto.getBidTypes();
        final String status = dto.getStatus();

        return new SearchCriteria(dto.getQuery(), dto.getMinBetPrice(), dto.getMaxBetPrice(),
                bidTypes == null ? null : bidTypes
                        .stream()
                        .map(bidType -> Bid.Type.valueOf(bidType.toUpperCase()))
                        .collect(Collectors.toList()),
                minStartDateSecondsGone == 0 ? null : LocalDateTime.now().minusSeconds(minStartDateSecondsGone),
                maxStartDateSecondsGone == 0 ? null : LocalDateTime.now().minusSeconds(maxStartDateSecondsGone),
                minExpirationDateSecondsLeft == 0 ? null : LocalDateTime.now().plusSeconds(minExpirationDateSecondsLeft),
                maxExpirationDateSecondsLeft == 0 ? null : LocalDateTime.now().plusSeconds(maxExpirationDateSecondsLeft),
                status == null ? Bid.Status.ACTIVE : Bid.Status.valueOf(status.toUpperCase()));
    }
}
