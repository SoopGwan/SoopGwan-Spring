package com.example.soopgwan.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_achive_success")
public class HabitSuccess {
    @Column(nullable = false)
    private boolean success;
}
