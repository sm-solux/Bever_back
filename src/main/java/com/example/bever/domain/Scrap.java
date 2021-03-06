package com.example.bever.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Scrap")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrapID")
    private Long scrapID;


    @ManyToOne()
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "recipeID")
    private UserRecipe userRecipe;

}
