package com.example.bever.repository;

import com.example.bever.domain.Scrap;
import com.example.bever.domain.User;
import com.example.bever.domain.UserRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap,Long> {
    List<Scrap> findAllByUser(User user);
    List<Scrap> findAllByUserAndUserRecipe(User user, UserRecipe userRecipe);
    void deleteByUserAndUserRecipe(User user, UserRecipe userRecipe);

    @Query(value = "select s.userRecipe.recipeID from Scrap s where s.user.userID=?1")
    List<Long> findUserAllScrapID(Long userID);

}
