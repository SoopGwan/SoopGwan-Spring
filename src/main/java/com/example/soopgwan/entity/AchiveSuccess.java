package com.example.soopgwan.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_habit_success")
public class AchiveSuccess {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "achive_id")
    private Achive achive;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM--dd")
    private LocalDate date;
}
