package com.example.soopgwan.domain.habit.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HabitId implements Serializable {

    @Column(columnDefinition = "BIGINT", nullable = false)
    private long habitId;
}
