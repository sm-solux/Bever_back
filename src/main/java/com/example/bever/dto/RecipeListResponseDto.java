package com.example.bever.dto;

import com.example.bever.domain.User;
import com.example.bever.domain.UserRecipe;
import com.example.bever.repository.ScrapRepository;
import com.example.bever.service.ScrapService;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RecipeListResponseDto {
    private Long recipeID;
    private Long drinkID;
    private Long writer; //Long 아이디로 받아오고
    private String title;
    private String content;
    private String imageLink;
    private LocalDateTime date;
    private String writerNickname;
    private Long scrapCount;
    private String drinkName;
    private String drinkOwner;
    private Boolean scrapped;

    @Builder
    public  RecipeListResponseDto(UserRecipe r){
        this.recipeID = r.getRecipeID();
        this.drinkID = r.getDrinks().getDrinkID();
        this.title = r.getRecipeName();
        this.content = r.getRecipeContent();
        this.imageLink = r.getImageLink();
        this.date = r.getRecipeDate();
        this.writer = r.getUser().getUserID();
        this.writerNickname = r.getUser().getNickname();
        this.scrapCount=r.getScrapCount();
        this.drinkName=r.getDrinks().getDrinkName();
        this.drinkOwner=r.getDrinks().getDrinkOwners().getTitle();
    }
}
