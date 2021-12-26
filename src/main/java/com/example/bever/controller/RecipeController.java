package com.example.bever.controller;

import com.example.bever.domain.Drinks;
import com.example.bever.domain.User;
import com.example.bever.domain.UserRecipe;
import com.example.bever.dto.RecipeSaveRequestDto;
import com.example.bever.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    @PostMapping("v1/recipe/post")
    public String post(@ModelAttribute RecipeSaveRequestDto recipeSaveRequestDto) {
        Drinks drink = recipeSaveRequestDto.getDrinkID();
        User user = recipeSaveRequestDto.getWriter();
        String recipeName =  recipeSaveRequestDto.getTitle();
        String recipeContent = recipeSaveRequestDto.getContent();
        String imageLink = recipeSaveRequestDto.getImageLink();
        LocalDateTime time = recipeSaveRequestDto.getDate();

        UserRecipe recipe = UserRecipe.builder().drinks(drink).user(user).recipeName(recipeName)
                    .recipeContent(recipeContent).imageLink(imageLink).recipeDate(time).build();

        recipeRepository.save(recipe);
        return "success";
    }

    @GetMapping("v1/recipe/list")
    public List<UserRecipe> list() {
        List<UserRecipe> recipeList = recipeRepository.findAll();

        return recipeList;
    }
}
