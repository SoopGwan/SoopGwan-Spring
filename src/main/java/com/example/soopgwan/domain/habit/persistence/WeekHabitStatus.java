package com.example.soopgwan.domain.habit.persistence;

import com.example.soopgwan.domain.user.persistence.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "tbl_week_habit_status")
@Entity
public class WeekHabitStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate startAt;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate endAt;

    @Column(columnDefinition = "INT(5) default 0", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setStatus(Integer status) {
        this.status = status;
    }
}
