package com.example.bever.repository;

import com.example.bever.domain.UserRecipe;
import com.example.bever.dto.RecipeListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface RecipeRepository extends JpaRepository<UserRecipe, Long> {
    List<UserRecipe> findAll();
    List<UserRecipe> findByRecipeID(Long recipeID);

    @Query(value = "select new com.example.bever.dto.RecipeListResponseDto( r.recipeID,  r.recipeName, r.recipeContent, r.imageLink,  r.recipeDate,  r.user.userID,r.user.nickname) from UserRecipe r")
    List<RecipeListResponseDto> getAll();


}

