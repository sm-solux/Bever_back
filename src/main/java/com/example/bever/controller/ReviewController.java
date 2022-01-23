package com.example.bever.controller;

import com.example.bever.domain.Review;
import com.example.bever.dto.ReviewRequestDto;
import com.example.bever.repository.ReviewRepository;
import com.example.bever.repository.UserRepository;
import com.example.bever.service.ImageService;
import com.example.bever.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    private final ImageService imageService;
    private final ReviewService reviewService;

    @PostMapping("v1/post/review")
    public String Post_Review(@RequestPart(value = "image", required = false) MultipartFile file, ReviewRequestDto reviewRequestDto) throws IOException {

        // 이미지 파일을 구글 버킷에 전송해 저장한 후 이미지 링크를 받아온다.
        String imagelink =imageService.uploadImage(file);

        // 생성된 이미지 링크와 함께 Post를 저장한다.
        try {
            reviewService.saveReviewPost(reviewRequestDto, imagelink);
            return "Success";
        }catch (Exception e){
            return "Fail to Save";
        }

    }
}
