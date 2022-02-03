package com.example.bever.domain;


import lombok.*;

import javax.persistence.*;
import java.sql.ConnectionBuilder;
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
    private Long recipeID;

    @ManyToOne()
    @JoinColumn(name = "drinkID")
    private Drinks drinks;

    @ManyToOne()
    @JoinColumn(name = "userID")
    private User user;

    @Column
    private String recipeName;

    @Column
    private String recipeContent;

    @Column
    private String imageLink;

    @Column
    private LocalDateTime recipeDate;


    @Column
    private Long scrapCount = Long.valueOf(0);


}
