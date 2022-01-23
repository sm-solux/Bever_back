package com.example.bever.service;

import com.example.bever.domain.Review;
import com.example.bever.dto.ReviewRequestDto;
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

    public void saveReviewPost(ReviewRequestDto reviewRequestDto, String imagelink){

        Review review = Review.builder()
                .content(reviewRequestDto.getContent())
                .date(LocalDateTime.now())
                .imageLink(imagelink)
                .rate(reviewRequestDto.getRate())
                .title(reviewRequestDto.getTitle())
                .user(userRepository.findAllByUserID(reviewRequestDto.getWriter()).get(0))
                .build();
        reviewRepository.save(review);
    }
}
