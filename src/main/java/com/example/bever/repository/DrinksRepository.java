package com.example.bever.repository;

import com.example.bever.domain.DrinkOwners;
import com.example.bever.domain.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinksRepository extends JpaRepository<Drinks,Long> {
    List<Drinks> findByDrinkID(Long drinkID);

    List<Drinks> findAllByDrinkNameAndDrinkOwners(String DrinkName, DrinkOwners drinkOwners);
}
