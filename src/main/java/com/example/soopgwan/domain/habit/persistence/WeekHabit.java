package com.example.soopgwan.domain.habit.persistence;

import com.example.soopgwan.domain.user.persistence.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "tbl_week_habit")
@Entity
public class WeekHabit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private String content;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate startAt;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate endAt;

    @Column(columnDefinition = "DATE")
    private LocalDate firstSuccessAt;

    @Column(columnDefinition = "DATE")
    private LocalDate lastSuccessAt;

    @Column(columnDefinition = "INT default 0", nullable = false)
    private Integer successCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void check() {
        this.lastSuccessAt = LocalDate.now();
        this.successCount += 1;
    }
}
