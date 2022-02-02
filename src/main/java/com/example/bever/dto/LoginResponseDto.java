package com.example.bever.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {
    private Long userID;
    private String pwWrong;
    private String emailNotExist;
    private String nickname;
}
