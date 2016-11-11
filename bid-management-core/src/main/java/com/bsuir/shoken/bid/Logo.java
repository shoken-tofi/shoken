package com.bsuir.shoken.bid;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "logos")
@SequenceGenerator(name = "entity_generator", sequenceName = "logos_seq")
class Logo extends BaseImage {
}
