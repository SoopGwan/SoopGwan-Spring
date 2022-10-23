package com.example.soopgwan.domain.habit.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "tbl_habit")
@Entity
public class Habit {
    @Id
    private long id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private String content;

    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDate startAt;

    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDate endAt;


}
