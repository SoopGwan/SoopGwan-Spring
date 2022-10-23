package com.example.soopgwan.domain.user.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "tbl_user")
public class User {

    @Id
    private long id;

    @Column(columnDefinition = "VARCHAR(24)", nullable = false)
    private String accountId;

    @Column(columnDefinition = "CHAR(60)", nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String name;

    @Column(columnDefinition = "CHAR(11)", nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "CHAR(3)", nullable = false)
    private String level;
}
 