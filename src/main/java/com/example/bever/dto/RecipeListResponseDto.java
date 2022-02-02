package com.example.bever.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeListResponseDto {
    private Long drinkID;
    private String title;
    private String content;
    private String imageLink;
    private LocalDateTime date;
    private Long writer; //Long 아이디로 받아오고
    private String writerNickname;
}
