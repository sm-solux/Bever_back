package com.example.bever.dto;

import com.example.bever.domain.Drinks;
import com.example.bever.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RecipeSaveRequestDto {
    private Drinks drinkID;
    private User writer;
    private String title;
    private String content;
    private String imageLink;
    private LocalDateTime date;
}
