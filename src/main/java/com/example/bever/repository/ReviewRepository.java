package com.example.bever.repository;

import com.example.bever.domain.DrinkOwners;
import com.example.bever.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByDrinkOwners(DrinkOwners drinkOwners);
}
