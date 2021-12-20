package com.example.bever.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterRequestDto {

    private String email;

    private String pw;
}
