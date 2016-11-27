package com.bsuir.shoken.bid;

import com.bsuir.shoken.BaseEntity;
import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "logo_id", nullable = false)
    private final Logo logo;

    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)

    @Entity
    @Table(name = "logos")
    @SequenceGenerator(name = "entity_generator", sequenceName = "logos_seq", allocationSize = 1)
    static class Logo extends BaseImage {

        Logo(String path) {
            super(path);
        }

        Logo(String path, String name, Extension extension) {
            super(path);
            setName(name);
            setExtension(extension);
        }
    }
}
