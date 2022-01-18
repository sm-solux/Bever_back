package com.example.bever.repository;

import com.example.bever.domain.UserRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<UserRecipe, Long> {
    List<UserRecipe> findAll();
    List<UserRecipe> findByRecipeID(Long recipeID);
}

