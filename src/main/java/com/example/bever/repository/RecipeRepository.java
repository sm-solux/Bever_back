package com.example.bever.repository;

import com.example.bever.domain.UserRecipe;
import com.example.bever.dto.RecipeListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface RecipeRepository extends JpaRepository<UserRecipe, Long> {
    List<UserRecipe> findAll();
    List<UserRecipe> findByRecipeID(Long recipeID);

    @Query(value = "select new com.example.bever.dto.RecipeListResponseDto(r) from UserRecipe r order by r.recipeDate")
    List<RecipeListResponseDto> getAll();

    @Transactional
    @Modifying
    @Query(value = "update UserRecipe u set u.scrapCount=u.scrapCount+1 where u.recipeID=?1")
    void updateScrapCountPlus(Long userRecipeID);

    @Transactional
    @Modifying
    @Query(value = "update UserRecipe u set u.scrapCount=u.scrapCount-1 where u.recipeID=?1")
    void updateScrapCountMinus(Long userRecipeID);
}

