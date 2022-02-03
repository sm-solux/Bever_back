package com.example.bever.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScrapPostRequestDto {
    private Long userID;
    private Long recipeID;
}
