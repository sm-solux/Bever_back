package com.example.bever.dto;

import lombok.*;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class RegisterRequestDto {

    private String email;

    private String pw;

    private String nickname;
}
