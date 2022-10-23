package com.example.soopgwan.domain.achivesuccess.domain;

import com.example.soopgwan.domain.achive.domain.AchiveId;
import com.example.soopgwan.domain.user.domain.UserId;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "tbl_achive_success")
@Entity
public class AchiveSuccess {

    @EmbeddedId
    private UserId userId;

    @EmbeddedId
    private AchiveId achiveId;

    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDate date;
}
