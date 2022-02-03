package com.example.bever.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewID")
    private Long reviewID;

    @ManyToOne()
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "drinkID")
    private Drinks drinks;

    @Column(name = "imageLink")
    private String imageLink;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "rate")
    private Double rate;

    @Enumerated(EnumType.STRING)
    private DrinkOwners drinkOwners;




}
