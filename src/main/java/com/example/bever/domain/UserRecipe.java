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
    @Column(name = "RecipeID")
    private Long RecipeID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DrinkID")
    private Drinks drinks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    private User user;

    @Column
    private String RecipeName;

    @Column
    private String RecipeContent;

    @Column
    private String ImageLink;

    @Column
    private LocalDateTime RecipeDate;

}
