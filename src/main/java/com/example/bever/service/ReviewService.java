package com.example.bever.service;

import com.example.bever.domain.DrinkOwners;
import com.example.bever.domain.Drinks;
import com.example.bever.domain.Review;
import com.example.bever.dto.CalendarPostRequestDto;
import com.example.bever.dto.ReviewRequestDto;
import com.example.bever.repository.DrinksRepository;
import com.example.bever.repository.ReviewRepository;
import com.example.bever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final DrinksRepository drinksRepository;

    public void saveReviewPost(ReviewRequestDto reviewRequestDto, String imagelink){

        DrinkOwners drinkOwners =DrinkOwners.STARBUCKS ;

        if(reviewRequestDto.getDrinkOwners().equals(DrinkOwners.STARBUCKS.getKey())){
            drinkOwners = DrinkOwners.STARBUCKS;
        }
        if(reviewRequestDto.getDrinkOwners().equals(DrinkOwners.TWOSOME.getKey())){
            drinkOwners = DrinkOwners.TWOSOME;
        }

        Long drinkID =reviewRequestDto.getDrinkID();
        Drinks drinks = drinksRepository.findByDrinkID(drinkID).get(0);

        Review review = Review.builder()
                .content(reviewRequestDto.getContent())
                .date(LocalDateTime.now())
                .imageLink(imagelink)
                .rate(reviewRequestDto.getRate())
                .title(reviewRequestDto.getTitle())
//                .user(userRepository.findAllByUserID(reviewRequestDto.getWriter()).get(0))
                .user(userRepository.findAllByUserID(reviewRequestDto.getWriter()).get(0))
                .drinkOwners(drinkOwners)
                .drinks(drinks)
                .build();
        reviewRepository.save(review);
    }
}
