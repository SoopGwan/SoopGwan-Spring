package com.example.soopgwan.domain.habit.persistence;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class HabitSuccessId implements Serializable {

    private Long id;

    private Long habit;
}
