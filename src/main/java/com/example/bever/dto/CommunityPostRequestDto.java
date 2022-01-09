package com.example.bever.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommunityPostRequestDto {
    private Long userID;
    private String title;
    private String imageLink;
    private String content;
    private String date;
}
