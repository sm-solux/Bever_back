package com.example.bever.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RecommendResponseDto {

    private Long userid;

    private List<DrinksDto> drinksDtoList;

}
