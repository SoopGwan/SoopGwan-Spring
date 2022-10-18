package com.example.soopgwan.domain.achive.domain;

import com.example.soopgwan.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "tbl_achive")
@Entity
public class Achive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT", nullable = false)
    private Long id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private String condition;

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}