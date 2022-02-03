package com.example.bever.controller;

import com.example.bever.domain.User;
import com.example.bever.dto.RegisterRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@NoArgsConstructor
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegister() throws Exception {
        String email = "test5@test.com";
        String pw = "test";

        RegisterRequestDto dto = RegisterRequestDto.builder().email(email).pw(pw).build();
        String url = "/v1/user/save";

        String object = objectMapper.writeValueAsString(dto);

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post(url).content(object).contentType(MediaType.APPLICATION_JSON));

        actions.andExpect(status().isOk());
    }
}
