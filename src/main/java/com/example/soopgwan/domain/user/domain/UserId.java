package com.example.soopgwan.domain.user.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserId implements Serializable {

    @Column(columnDefinition = "BIGINT", nullable = false)
    private long userId;
}
