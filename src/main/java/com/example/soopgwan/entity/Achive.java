package com.example.soopgwan.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_achive")
public class Achive {
    @Column(length = 200, nullable = false)
    private String condition;

    @Column(length = 10, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
