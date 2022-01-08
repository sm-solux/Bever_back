package com.example.bever.controller;

import com.example.bever.domain.User;
import com.example.bever.dto.RegisterRequestDto;
import com.example.bever.dto.RegisterResponseDto;
import com.example.bever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepository;

    @ResponseBody
    @PostMapping("v1/user/save")
    public RegisterResponseDto register(@ModelAttribute RegisterRequestDto registerRequestDto){
        List<User> findbyemail = userRepository.findUsersByUserEmail(registerRequestDto.getEmail());
        if(findbyemail.size()!=0){
            RegisterResponseDto registerResponseDto = RegisterResponseDto.builder().alreadyExist("User Email already exist").build();
            return registerResponseDto;
        }

        User user = User.builder().userEmail(registerRequestDto.getEmail()).userPw(registerRequestDto.getPw()).build();
        userRepository.save(user);

        RegisterResponseDto registerResponseDto = RegisterResponseDto.builder().userID(user.getUserID()).build();
        return registerResponseDto;
    }

    @ResponseBody
    @PostMapping("v1/user/login")
    public Long login(@ModelAttribute  RegisterRequestDto registerRequestDto){
//        List<User> users = userRepository.findAllByUserEmailAndUserPw(registerRequestDto.getEmail(),registerRequestDto.getPw());
        List<User> users = userRepository.findAllByUserEmailAndUserPw(registerRequestDto.getEmail(),registerRequestDto.getPw());

        return users.get(0).getUserID();
    }


}
