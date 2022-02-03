package com.example.bever.dto;

import com.example.bever.domain.DrinkOwners;
import com.example.bever.domain.Review;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewListByOwnerResponseDto {

    private List<ReviewListResponseDto> list;
    private DrinkOwners owners;

}
