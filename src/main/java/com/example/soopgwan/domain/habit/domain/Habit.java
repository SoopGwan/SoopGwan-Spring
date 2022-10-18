package com.example.soopgwan.domain.habit.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_habit")
@Entity
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT", nullable = false)
    private long id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM--dd")
    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDate startAt;

    @DateTimeFormat(pattern = "yyyy-MM--dd")
    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDate endAt;


}
