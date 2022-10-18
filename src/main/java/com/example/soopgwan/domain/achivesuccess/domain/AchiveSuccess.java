package com.example.soopgwan.domain.achivesuccess.domain;

import com.example.soopgwan.domain.achive.domain.Achive;
import com.example.soopgwan.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_achive_success")
@Entity
public class AchiveSuccess {

    @EmbeddedId
    private
    private User user;

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name = "achive_id")
    @Column(columnDefinition = "BIGINT", nullable = false)
    private Achive achive;

    @DateTimeFormat(pattern = "yyyy-MM--dd")
    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDate date;
}
