package com.example.bever.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CalendarPostRequestDto {
    private Long userID;
    private List<Long> drinkID;
    private String date;
}
