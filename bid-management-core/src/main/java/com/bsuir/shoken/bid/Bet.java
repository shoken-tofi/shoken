package com.bsuir.shoken.bid;

import com.bsuir.shoken.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "bets")
@SequenceGenerator(name = "entity_generator", sequenceName = "bets_seq", allocationSize = 1)
public class Bet extends BaseEntity {

    @Column(name = "investor_id", nullable = false, updatable = false)
    private final Long investorId;

    @Column(name = "bid_id", nullable = false, updatable = false)
    private final Long bidId;

    @Column(nullable = false, precision = 2, scale = 12)
    private final BigDecimal value;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime = LocalDateTime.now();
}
