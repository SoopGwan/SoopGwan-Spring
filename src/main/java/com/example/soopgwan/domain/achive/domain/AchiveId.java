package com.example.soopgwan.domain.achive.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AchiveId implements Serializable {

    @Column(columnDefinition = "BIGINT", nullable = false)
    private long achiveId;
}
