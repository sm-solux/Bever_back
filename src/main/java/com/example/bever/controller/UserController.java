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
    public RegisterResponseDto register(@RequestBody RegisterRequestDto registerRequestDto){
        User user = User.builder().UserEmail(registerRequestDto.getEmail()).UserPw(registerRequestDto.getPw()).build();
        userRepository.save(user);

        RegisterResponseDto registerResponseDto = RegisterResponseDto.builder().userID(user.getUserID()).build();
        return registerResponseDto;
    }

    @ResponseBody
    @PostMapping("v1/user/login")
    public String login(@RequestBody  RegisterRequestDto registerRequestDto){
        List<User> users = userRepository.findAllByUserEmailAndUserPw(registerRequestDto.getEmail(),registerRequestDto.getPw());

        if( users.size() == 0 )
            return "fail";

        return "success";
    }


}
