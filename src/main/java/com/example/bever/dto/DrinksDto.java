package com.example.bever.dto;

import com.example.bever.domain.DrinkOwners;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DrinksDto {
    private Long drinkId;
    private String drinkName;
    private String  drinkOwners;
    private String drinkImageLink;
}
