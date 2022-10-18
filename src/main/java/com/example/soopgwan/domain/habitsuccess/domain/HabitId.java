package com.example.soopgwan.domain.habitsuccess.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor

public class HabitId implements Serializable {

    @Column(columnDefinition = "BIGINT", nullable = false)
    private long habitId;
}
