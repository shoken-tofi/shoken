package com.bsuir.shoken.payment;

import com.bsuir.shoken.AuctionParticipant;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "vendees")
@SequenceGenerator(name = "entity_generator", sequenceName = "vendees_seq", allocationSize = 1)
class Vendee extends AuctionParticipant {

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id", nullable = false)
    private final Bill bill;

    Vendee(String name, Bill bill) {
        super(name);
        this.bill = bill;
    }
}
