package com.example.bever.controller;

import com.example.bever.domain.Drinks;
import com.example.bever.domain.User;
import com.example.bever.domain.UserRecipe;
import com.example.bever.dto.RecipeListResponseDto;
import com.example.bever.dto.RecipePostRequestDto;
import com.example.bever.dto.RecipeSaveRequestDto;
import com.example.bever.repository.DrinksRepository;
import com.example.bever.repository.RecipeRepository;
import com.example.bever.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final DrinksRepository drinksRepository;

    @PostMapping("v1/recipe/post")
    public String post(@RequestBody RecipePostRequestDto recipePostRequestDto) {
        Long drinkID = recipePostRequestDto.getDrinkID();
        //UserRepository 에서 id로 User 검색해서 user에 넣기
        Long userID = recipePostRequestDto.getWriter();
        String recipeName =  recipePostRequestDto.getTitle();
        String recipeContent = recipePostRequestDto.getContent();
        String imageLink = recipePostRequestDto.getImageLink();
        String time = recipePostRequestDto.getDate();
        //null값 체크-list의 길이가 0이면 return "error";

        List<Drinks> drink = drinksRepository.findByDrinkID(drinkID);
        List<User> user = userRepository.findAllByUserID(userID);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        if(drink.size()==0) {
            return "fail";
        }
        if(user.size()==0) {
            return "fail";
        }

        UserRecipe recipe = UserRecipe.builder().drinks(drink.get(0)).user(user.get(0)).recipeName(recipeName)
                .recipeContent(recipeContent).imageLink(imageLink).recipeDate(dateTime).build();

        recipeRepository.save(recipe);
        return "success";
    }

    @GetMapping("v1/recipe/list")
    public List<RecipeListResponseDto> list() {
        List<RecipeListResponseDto> recipeList = recipeRepository.getAll();
//        List<UserRecipe> recipeList = recipeRepository.
        return recipeList;
    }
}
