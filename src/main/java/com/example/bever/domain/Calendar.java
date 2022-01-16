package com.example.bever.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Calendar")

public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendarID")
    private Long calenderID;

    @ManyToOne()
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "drinkID")
    private Drinks drinks;

    @Column(nullable = false)
    private String month;

    @Column(nullable = false)
    private String date;
}


