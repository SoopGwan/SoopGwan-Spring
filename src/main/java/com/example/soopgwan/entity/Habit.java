package com.example.soopgwan.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_habit")
public class Habit {
    @Column(length = 200, nullable = false)
    private String content;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM--dd")
    private LocalDate startAt;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM--dd")
    private LocalDate endAt;


}
