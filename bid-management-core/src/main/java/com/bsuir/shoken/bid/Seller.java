package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PACKAGE)

@Entity
@Table(name = "sellers")
@SequenceGenerator(name = "entity_generator", sequenceName = "sellers_seq", allocationSize = 1, initialValue = 2)
class Seller extends AuctionParticipant {

    Seller(String name) {
        super(name);
    }
}
