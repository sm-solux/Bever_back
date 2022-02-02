package com.example.bever.repository;

import com.example.bever.domain.DrinkOwners;
import com.example.bever.domain.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrinksRepository extends JpaRepository<Drinks,Long> {
    List<Drinks> findByDrinkID(Long drinkID);

    List<Drinks> findAllByDrinkNameAndDrinkOwners(String DrinkName, DrinkOwners drinkOwners);

//    Drinks findTopByDrinkID
    @Query(" SELECT max(d.drinkID)from Drinks d")
    Long maxdrinkID();
}
