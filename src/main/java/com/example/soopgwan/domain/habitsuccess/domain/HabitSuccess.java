package com.example.soopgwan.domain.habitsuccess.domain;

import com.example.soopgwan.domain.achive.domain.Achive;
import com.example.soopgwan.domain.habit.domain.Habit;
import com.example.soopgwan.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_achive_success")
@Entity
public class HabitSuccess {

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Column(columnDefinition = "BIGINT", nullable = false)
    private User user;

    @EmbeddedId
    @JoinColumn(name = "habit_id")
    @Column(columnDefinition = "BIGINT", nullable = false)
    private Habit habit;

    @Column(columnDefinition = "BOOLEAN", nullable = false)
    private boolean success;
}
