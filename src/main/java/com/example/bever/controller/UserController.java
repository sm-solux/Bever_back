package com.example.bever.controller;

import com.example.bever.domain.User;
import com.example.bever.dto.*;
import com.example.bever.repository.UserRepository;
import com.example.bever.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepository;
    private final RecommendService recommendService;

    @ResponseBody
    @PostMapping("v1/user/save")
    public RegisterResponseDto register( RegisterRequestDto registerRequestDto){
        System.out.println(registerRequestDto.getEmail());
        List<User> findbyemail = userRepository.findUsersByUserEmail(registerRequestDto.getEmail());
        if(findbyemail.size()!=0){
            RegisterResponseDto registerResponseDto = RegisterResponseDto.builder().alreadyExist("User Email already exist").build();
            return registerResponseDto;
        }

        User user = User.builder().userEmail(registerRequestDto.getEmail()).userPw(registerRequestDto.getPw()).nickname(registerRequestDto.getNickname()).build();

        userRepository.save(user);

        RegisterResponseDto registerResponseDto = RegisterResponseDto.builder().userID(user.getUserID()).nickname(user.getNickname()).build();
        return registerResponseDto;
    }

    @ResponseBody
    @PostMapping("v1/user/login")
    public LoginResponseDto login(@ModelAttribute  RegisterRequestDto registerRequestDto){
        List<User> users = userRepository.findAllByUserEmailAndUserPw(registerRequestDto.getEmail(),registerRequestDto.getPw());


        if (users.size()==0){
            if(userRepository.findUsersByUserEmail(registerRequestDto.getEmail()).size()==0) {
                LoginResponseDto responseDto = LoginResponseDto.builder().emailNotExist("true").build();
                return responseDto;
            }
            LoginResponseDto responseDto = LoginResponseDto.builder().pwWrong("true").build();
            return responseDto;
        }
        LoginResponseDto responseDto = LoginResponseDto.builder().userID(users.get(0).getUserID()).nickname(users.get(0).getNickname()).build();
        return responseDto;
    }

    @ResponseBody
    @GetMapping("v1/user/recommend/{userId}")
    public RecommendResponseDto recommend(@PathVariable Long userId){
        List<DrinksDto> drinksDtoList = recommendService.recommend(userId);

        RecommendResponseDto recommendResponseDto = RecommendResponseDto.builder().userid(userId).drinksDtoList(drinksDtoList).build();
        return recommendResponseDto;
    }

}