package com.example.bever.controller;

import com.example.bever.domain.Scrap;
import com.example.bever.domain.User;
import com.example.bever.domain.UserRecipe;
import com.example.bever.dto.RecipeListResponseDto;
import com.example.bever.dto.ScrapPostRequestDto;
import com.example.bever.repository.RecipeRepository;
import com.example.bever.repository.ScrapRepository;
import com.example.bever.repository.UserRepository;
import com.example.bever.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ScrapController {
    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final ScrapService scrapService;

    @PostMapping("v1/scrap/post")
    public String post(@RequestBody ScrapPostRequestDto scrapPostRepository) {

        return scrapService.scrapping(scrapPostRepository,true);

    }

    @PostMapping("v1/scrap/unpost")
    public String unpost(@RequestBody ScrapPostRequestDto scrapPostRepository){
        return scrapService.scrapping(scrapPostRepository,false);
    }

    @PostMapping("v1/scrap/list/{userID}")
    public List<RecipeListResponseDto> userScrapList(@PathVariable Long userID) {
        List<User> user = userRepository.findAllByUserID(userID);

        return scrapService.returnScrapRecipeList(user.get(0));
    }
}
