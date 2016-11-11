package com.bsuir.shoken.bid;

import com.bsuir.shoken.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@MappedSuperclass
abstract class BaseImage extends BaseEntity {

    @Column(nullable = false)
    private final String path;

    @Column(nullable = false)
    private final String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private final Extension extension;

    enum Extension {

        JPG, PNG
    }
}
