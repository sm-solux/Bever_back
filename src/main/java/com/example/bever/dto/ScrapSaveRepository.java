package com.example.bever.dto;

import com.example.bever.domain.User;
import com.example.bever.domain.UserRecipe;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScrapSaveRepository {
    private Long userID;
    private Long recipeID;
}
