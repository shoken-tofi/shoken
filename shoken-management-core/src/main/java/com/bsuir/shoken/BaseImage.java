package com.bsuir.shoken;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@MappedSuperclass
public abstract class BaseImage extends BaseEntity {

    private String path;

    @Column(nullable = false)
    private final String name;

    @Enumerated(EnumType.STRING)
    private Extension extension;

    public enum Extension {

        JPG, PNG, BMP
    }
}
