package com.example.bever.service;


import com.example.bever.domain.DrinkOwners;
import com.example.bever.domain.Drinks;
import com.example.bever.domain.User;
import com.example.bever.domain.UserRecipe;
import com.example.bever.dto.RecipePostRequestDto;
import com.example.bever.repository.DrinksRepository;
import com.example.bever.repository.RecipeRepository;
import com.example.bever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeService {
    private final UserRepository userRepository;
    private final DrinksRepository drinksRepository;
    private final RecipeRepository recipeRepository;

    public void saveRecipePost(RecipePostRequestDto recipePostRequestDto, String imagelink){
        User user=userRepository.findAllByUserID(recipePostRequestDto.getWriter()).get(0);

        Long drinkID =recipePostRequestDto.getDrinkID();
        Drinks drinks = drinksRepository.findByDrinkID(drinkID).get(0);

        UserRecipe userRecipe = UserRecipe.builder()
                .drinks(drinks)
                .user(user)
                .imageLink(imagelink)
                .recipeContent(recipePostRequestDto.getContent())
                .recipeDate(LocalDateTime.now())
                .recipeName(recipePostRequestDto.getTitle())
                .scrapCount(Long.valueOf(0))
                .build();
        recipeRepository.save(userRecipe);
    }
}
