package com.example.soopgwan.domain.achieve.persistence;

import com.example.soopgwan.domain.achieve.enums.AchieveType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Table(name = "tbl_achieve")
@Entity
public class Achieve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private String content;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String rarityType;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String productType;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String title;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private AchieveType code;
}
