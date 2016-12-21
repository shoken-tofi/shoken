package com.bsuir.shoken.payment;

import com.bsuir.shoken.AuctionParticipant;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "vendors")
@SequenceGenerator(name = "entity_generator", sequenceName = "vendors_seq", allocationSize = 1)
class Vendor extends AuctionParticipant {

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id", nullable = false)
    private final Bill bill;

    Vendor(String name, Bill bill) {
        super(name);
        this.bill = bill;
    }
}
