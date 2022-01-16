package com.example.bever.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CalendarPostRequestDto {
    private Long userID;
    private Long drinkID;
//    private String date;
    private String month;
    private String date;
}
