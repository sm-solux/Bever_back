package com.example.bever.repository;

import com.example.bever.domain.Drinks;
import com.example.bever.domain.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public interface RecipeList {
    Long getRecipeID();

//    Drinks drinks;

    Long getUserID();

    String getUserNickname();

    String getRecipeName();

     String getRecipeContent();

     String getImageLink();

    LocalDateTime getRecipeDate();
}
