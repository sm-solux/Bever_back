package com.example.bever.service;

import com.example.bever.domain.Scrap;
import com.example.bever.domain.User;
import com.example.bever.domain.UserRecipe;
import com.example.bever.dto.RecipeListResponseDto;
import com.example.bever.dto.ScrapPostRequestDto;
import com.example.bever.repository.RecipeRepository;
import com.example.bever.repository.ScrapRepository;
import com.example.bever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;



    public String scrapping(ScrapPostRequestDto scrapPostRepository, Boolean isScrap){
        Long userID = scrapPostRepository.getUserID();
        Long recipeID = scrapPostRepository.getRecipeID();

        List<User> user = userRepository.findAllByUserID(userID);
        List<UserRecipe> userRecipe = recipeRepository.findByRecipeID(recipeID);
        if(user.size()==0) {
            return "fail, nouser";
        }
        if(userRecipe.size()==0) {
            return "fail, norecipe";
        }
        List<Scrap> scrapList = scrapRepository.findAllByUserAndUserRecipe(user.get(0), userRecipe.get(0));


        if(isScrap){return scrap(scrapList, user, userRecipe);}
        else {return unscrap(scrapList, user, userRecipe);}
    }

    private String scrap(List<Scrap> scrapList,List<User> user,List<UserRecipe> userRecipe){
        if(scrapList.size()!=0) { //이미 스크랩 했는지 확인
            return "fail, already scrapped";
        }

        recipeRepository.updateScrapCountPlus(userRecipe.get(0).getRecipeID());

        Scrap scrap = Scrap.builder().user(user.get(0)).userRecipe(userRecipe.get(0)).build();

        scrapRepository.save(scrap);
        return "success";
    }

    private String unscrap(List<Scrap> scrapList, List<User> user,List<UserRecipe> userRecipe){
        if(scrapList.size()==0) { //이미 스크랩 했는지 확인
            return "fail, didn't scrapped";
        }
        recipeRepository.updateScrapCountMinus(userRecipe.get(0).getRecipeID());
        try {
            scrapRepository.deleteByUserAndUserRecipe(user.get(0), userRecipe.get(0));
        }catch (Exception e){return String.valueOf(e);}
        return "success";
    }

    public List<RecipeListResponseDto> returnScrapRecipeList(User user){
        return getScrapList(user);
    }

    private List<RecipeListResponseDto> getScrapList(User user){
        List<Scrap> scrapList =  scrapRepository.findAllByUser(user);
        System.out.println(user.getUserID());
        List<RecipeListResponseDto> recipelist= new LinkedList<>();
        scrapList.forEach(scrap -> {
            System.out.println(scrap.getScrapID());
            recipelist.add( new RecipeListResponseDto(scrap.getUserRecipe()) );
        });
        return recipelist;
    }

    public List<Long> returnScrapCount(Long userID){
        return scrapRepository.findUserAllScrapID(userID);
    }
}
