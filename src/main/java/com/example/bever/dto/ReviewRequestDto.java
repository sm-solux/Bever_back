package com.example.bever.dto;

import com.example.bever.domain.DrinkOwners;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequestDto {

    private Long writer; // "글 작성자 아이디",
    private String title; // "게시글 제목",
    private String content; // "게시글내용",
    private Double rate; // ”별점”
    private String drinkOwners;
    private MultipartFile file;
    private String drinkname;
    private Long drinkID;

}
