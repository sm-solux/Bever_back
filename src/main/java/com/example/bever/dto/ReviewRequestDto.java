package com.example.bever.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequestDto {
    private String drinkID; // "음료아이디",
    private Long writer; // "글 작성자 아이디",
    private String title; // "게시글 제목",
    private String content; // "게시글내용",
    private String imageLink; // "게시글 이미지 존재 시 이미지 링크",
    private LocalDateTime date; // "글 작성날짜",
    private Double rate; // ”별점”
}
