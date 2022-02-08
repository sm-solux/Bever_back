package com.example.bever.repository;

import com.example.bever.domain.DrinkOwners;
import com.example.bever.domain.Review;
import com.example.bever.dto.ReviewListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query(value = "select new com.example.bever.dto.ReviewListResponseDto(r) from Review r where r.drinkOwners=?1 order by r.date")
    List<ReviewListResponseDto> findAllByDrinkOwners(DrinkOwners drinkOwners);
}
