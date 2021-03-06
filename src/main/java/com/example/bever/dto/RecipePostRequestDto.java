package com.example.bever.dto;

import com.example.bever.domain.Drinks;
import com.example.bever.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Builder
public class RecipePostRequestDto {
    private Long drinkID;
    private Long writer; //Long 아이디로 받아오고
    private String title;
    private String content;
    private MultipartFile file;
    private String date;
}
