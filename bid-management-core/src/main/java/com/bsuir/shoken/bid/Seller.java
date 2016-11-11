package com.bsuir.shoken.bid;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sellers")
@SequenceGenerator(name = "entity_generator", sequenceName = "sellers_seq")
class Seller extends AuctionParticipant {
}
