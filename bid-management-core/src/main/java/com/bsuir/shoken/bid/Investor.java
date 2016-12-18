package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PACKAGE)

@Entity
@Table(name = "investors")
@SequenceGenerator(name = "entity_generator", sequenceName = "investors_seq", allocationSize = 1)
class Investor extends AuctionParticipant {

    Investor(String name) {
        super(name);
    }
}
