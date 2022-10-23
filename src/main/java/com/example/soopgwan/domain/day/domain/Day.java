package com.example.soopgwan.domain.day.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "tbl_day")
@Entity
public class Day {
    @Id
    private Long id;

    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime startAt;

    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime endAt;

    @Column(columnDefinition = "BIGINT", nullable = false)
    private LocalDateTime eval;
}
