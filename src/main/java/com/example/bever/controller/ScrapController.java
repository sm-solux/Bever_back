package com.example.bever.controller;

import com.example.bever.domain.Scrap;
import com.example.bever.domain.User;
import com.example.bever.domain.UserRecipe;
import com.example.bever.dto.ScrapSaveRepository;
import com.example.bever.repository.RecipeRepository;
import com.example.bever.repository.ScrapRepository;
import com.example.bever.repository.UserRepository;
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

    @PostMapping("v1/scrap/post")
    public String post(@RequestBody ScrapSaveRepository scrapSaveRepository) {
        Long userID = scrapSaveRepository.getUserID(); //UserRepository에서 ID로 User 가져오기
        Long recipeID = scrapSaveRepository.getRecipeID();

        List<User> user = userRepository.findAllByUserID(userID);
        List<UserRecipe> userRecipe = recipeRepository.findByRecipeID(recipeID);
        if(user.size()==0) {
            return "fail";
        }
        if(userRecipe.size()==0) {
            return "fail";
        }
        List<Scrap> scrapList = scrapRepository.findAllByUserAndUserRecipe(user.get(0), userRecipe.get(0));
        if(scrapList.size()!=0) { //이미 스크랩 했는지 확인
            return "fail";
        }


        Scrap scrap = Scrap.builder().user(user.get(0)).userRecipe(userRecipe.get(0)).build();

        scrapRepository.save(scrap);
        return "success";
    }

    @GetMapping("v1/scrap/{userID}")
    public List<UserRecipe> getParameters(@RequestParam(name="userID") Long userID) {
        List<User> user = userRepository.findAllByUserID(userID);

        List<UserRecipe> userRecipes =  scrapRepository.findAllByUser(user.get(0));

        return userRecipes;
    }
}
