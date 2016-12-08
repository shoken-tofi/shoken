package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PACKAGE)

@Entity
@Table(name = "sellers")
@SequenceGenerator(name = "entity_generator", sequenceName = "sellers_seq", allocationSize = 1)
class Seller extends AuctionParticipant {

    Seller(Long userId, String name) {
        super(userId, name);
    }
}
