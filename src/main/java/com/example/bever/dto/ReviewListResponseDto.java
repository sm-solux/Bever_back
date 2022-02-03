package com.example.bever.dto;

import com.example.bever.domain.DrinkOwners;
import com.example.bever.domain.Review;
import com.example.bever.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewListResponseDto {

    private Long reviewID;

    private String userNickname;

    private Long userID;

    private String imageLink;

    private String title;

    private String content;

    private LocalDateTime date;

    private Double rate;

    private DrinkOwners drinkOwners;

    private Long drinkID;

    private String drinkName;

    public ReviewListResponseDto(Review review){
        this.reviewID=review.getReviewID();
        this.userNickname=review.getUser().getNickname();
        this.title=review.getTitle();
        this.content=review.getContent();
        this.date=review.getDate();
        this.imageLink=review.getImageLink();
        this.drinkOwners=review.getDrinkOwners();
        this.rate=review.getRate();
        this.userID=review.getUser().getUserID();
        this.drinkID=review.getDrinks().getDrinkID();
        this.drinkName=review.getDrinks().getDrinkName();
    }
}
