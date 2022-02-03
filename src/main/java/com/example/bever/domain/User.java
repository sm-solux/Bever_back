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
    @Column(name = "userID")
    private Long userID;

    private String userEmail;

    private String userPw;

    @Column
    private String nickname;

    public User(Long userID, String nickname){
        this.nickname = nickname;
        this.userID = userID;
    }
}
