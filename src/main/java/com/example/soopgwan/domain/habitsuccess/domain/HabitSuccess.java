package com.example.soopgwan.domain.habitsuccess.domain;

import com.example.soopgwan.domain.habit.domain.HabitId;
import com.example.soopgwan.domain.user.domain.UserId;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "tbl_achive_success")
@Entity
public class HabitSuccess {

    @EmbeddedId
    private UserId userId;

    @EmbeddedId
    private HabitId habitId;

    @Column(columnDefinition = "BOOLEAN", nullable = false)
    private boolean success;
}
