package com.example.bever.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long UserID;

    @Column(nullable = false)
    private String UserEmail;

    @Column(nullable = false)
    private String UserPw;
}
