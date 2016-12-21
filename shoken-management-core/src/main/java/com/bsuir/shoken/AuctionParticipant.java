package com.bsuir.shoken;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@MappedSuperclass
public abstract class AuctionParticipant extends BaseEntity {

    @Column(nullable = false, unique = true)
    private final String name;
}
