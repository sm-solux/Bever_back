package com.example.bever.controller;


import com.example.bever.domain.Community;
import com.example.bever.domain.User;
import com.example.bever.dto.CommunityPostRequestDto;
import com.example.bever.repository.CommunityRepository;
import com.example.bever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")

public class CommunityController {
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;

    @PostMapping("v1/community/post")
    public String post(@RequestBody CommunityPostRequestDto communityPostRequestDto) {
        Long userID = communityPostRequestDto.getUserID();
        String title = communityPostRequestDto.getTitle();
        String imageLink = communityPostRequestDto.getImageLink();
        String content = communityPostRequestDto.getContent();
        String time = communityPostRequestDto.getDate();

        List<User> user = userRepository.findAllByUserID(userID);
        if(user.size()!=1) {
            return "fail";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);

        Community community = Community.builder().user(user.get(0)).postName(title)
                .postContent(content).imageLink(imageLink).postDate(dateTime).build();

        communityRepository.save(community);
        return "success";
    }

    @GetMapping("v1/community/list")
    public List<Community> list() {
        List<Community> communityList = communityRepository.findAll();
        return communityList;
    }

    @GetMapping("v1/community/{userID}")
    public List<Community> getParameters(@RequestParam(name="userID") Long userID) {
        List<User> user = userRepository.findAllByUserID(userID);

        List<Community> communities = communityRepository.findAllByUser(user.get(0));

        return communities;
    }

}
