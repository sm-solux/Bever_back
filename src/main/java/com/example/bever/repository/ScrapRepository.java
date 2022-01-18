package com.example.bever.repository;

import com.example.bever.domain.Scrap;
import com.example.bever.domain.User;
import com.example.bever.domain.UserRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap,Long> {
    List<UserRecipe> findAllByUser(User user);
    List<Scrap> findAllByUserAndUserRecipe(User user, UserRecipe userRecipe);
}
