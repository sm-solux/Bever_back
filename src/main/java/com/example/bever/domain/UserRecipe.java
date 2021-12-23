package com.example.bever.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "UserRecipe")
public class UserRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipeID")
    private Long RecipeID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drinkID")
    private Drinks drinks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    private User user;

    @Column
    private String recipeName;

    @Column
    private String recipeContent;

    @Column
    private String rmageLink;

    @Column
    private LocalDateTime recipeDate;

}