package com.example.bever.controller;

import com.example.bever.domain.Drinks;
import com.example.bever.domain.User;
import com.example.bever.domain.UserRecipe;
import com.example.bever.dto.RecipeListResponseDto;
import com.example.bever.dto.RecipePostRequestDto;
import com.example.bever.repository.DrinksRepository;
import com.example.bever.repository.RecipeRepository;
import com.example.bever.repository.UserRepository;

import com.example.bever.service.ImageService;
import com.example.bever.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final DrinksRepository drinksRepository;
    private final ImageService imageService;
    private final RecipeService recipeService;

    @PostMapping("v1/recipe/post")
    public String post( RecipePostRequestDto recipePostRequestDto) throws IOException {

        // 이미지 파일을 구글 버킷에 전송해 저장한 후 이미지 링크를 받아온다.
        String imagelink ="";
        if(!recipePostRequestDto.getFile().isEmpty())
            imagelink =imageService.uploadImage(recipePostRequestDto.getFile());

        // 생성된 이미지 링크와 함께 Post를 저장한다.
        try {
            recipeService.saveRecipePost(recipePostRequestDto, imagelink);
            return "Success";
        }catch (Exception e){
            System.out.println(e);
            return "Fail to Save";
        }

    }

    @GetMapping("v1/recipe/list")
    public List<RecipeListResponseDto> list() {
        List<RecipeListResponseDto> recipeList = recipeRepository.getAll();
//        List<UserRecipe> recipeList = recipeRepository.
        return recipeList;
    }


}
