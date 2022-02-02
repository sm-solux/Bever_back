package com.example.bever.controller;

import com.example.bever.domain.DrinkOwners;
import com.example.bever.domain.Review;
import com.example.bever.dto.ReviewListByOwnerResponseDto;
import com.example.bever.dto.ReviewRequestDto;
import com.example.bever.repository.ReviewRepository;
import com.example.bever.repository.UserRepository;
import com.example.bever.service.ImageService;
import com.example.bever.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    private final ImageService imageService;
    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @PostMapping("v1/post/review")

    public String Post_Review(ReviewRequestDto requestDto) throws IOException {

        System.out.println(requestDto.getContent());
//        System.out.println(requestDto.getFile().getName());
        // 이미지 파일을 구글 버킷에 전송해 저장한 후 이미지 링크를 받아온다.
        String imagelink =imageService.uploadImage(requestDto.getFile());


        // 생성된 이미지 링크와 함께 Post를 저장한다.
        try {
            reviewService.saveReviewPost(requestDto, imagelink);
            return "Success";
        }catch (Exception e){
            System.out.println(e);
            return "Fail to Save";
        }


    }

    @GetMapping("v1/review/get/{owners}")
    public ReviewListByOwnerResponseDto drink_review_list(@PathVariable String owners){
        System.out.println(owners);
        List<Review> lists =reviewRepository.findAllByDrinkOwners(DrinkOwners.valueOf(owners));

        ReviewListByOwnerResponseDto responseDto = ReviewListByOwnerResponseDto.builder().owners(DrinkOwners.valueOf(owners)).list(lists).build();
        return responseDto;
    }
}
