package com.example.soopgwan.domain.habit.persistence;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tbl_habit_success")
@Entity
public class HabitSuccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate successAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_habit_id", nullable = false)
    private WeekHabit weekHabit;
}
