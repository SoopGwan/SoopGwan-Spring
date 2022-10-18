package com.example.soopgwan.domain.achivesuccess.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class AchiveId implements Serializable {

    @Column(columnDefinition = "BIGINT", nullable = false)
    private long achiveId;
}
