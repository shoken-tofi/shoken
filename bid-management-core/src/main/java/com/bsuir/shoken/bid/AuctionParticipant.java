package com.bsuir.shoken.bid;

import com.bsuir.shoken.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@MappedSuperclass
abstract class AuctionParticipant extends BaseEntity {

    @Column(nullable = false)
    private final String name;

    @Column(name = "registration_date", nullable = false)
    private final LocalDate registrationDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "logo_id", nullable = false)
    private final Logo logo;
}
