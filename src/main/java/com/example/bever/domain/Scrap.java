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
    @Column(name = "ScrapID")
    private Long ScrapID;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RecipeID")
    private UserRecipe userRecipe;

}
