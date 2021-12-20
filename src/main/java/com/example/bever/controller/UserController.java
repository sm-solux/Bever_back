package com.example.bever.controller;

import com.example.bever.domain.User;
import com.example.bever.dto.RegisterRequestDto;
import com.example.bever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepository;

    @ResponseBody
    @GetMapping("v1/user/save")
    public Long register(@RequestBody RegisterRequestDto registerRequestDto){
        User user = User.builder().UserEmail(registerRequestDto.getEmail()).UserPw(registerRequestDto.getPw()).build();
        userRepository.save(user);

        return user.getUserID();
    }
}
