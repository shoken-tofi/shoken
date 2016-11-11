package com.bsuir.shoken.bid;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "investors")
@SequenceGenerator(name = "entity_generator", sequenceName = "investors_seq")
class Investor extends AuctionParticipant {
}
