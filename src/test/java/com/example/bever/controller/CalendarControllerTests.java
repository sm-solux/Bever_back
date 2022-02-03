package com.example.bever.controller;

import com.example.bever.dto.CalendarPostRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@NoArgsConstructor
@SpringBootTest
@RunWith(SpringRunner.class)
public class CalendarControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPost() throws Exception {
        Long userID = Long.valueOf(1);
        List<Long> drinkID = new ArrayList<Long>();
        drinkID.add(Long.valueOf(1));
        String date = "2022-01-24 10:00";
        CalendarPostRequestDto dto = CalendarPostRequestDto.builder()
                .userID(userID).drinkID(drinkID).date(date).build();

        String object = objectMapper.writeValueAsString(dto);

        String url = "/v1/calendar/post";

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post(url).content(object)
                .contentType(MediaType.APPLICATION_JSON));

        actions.andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 음료ID로 테스트")
    @Test
    public void testPostFail() throws Exception {
        Long userID = Long.valueOf(1);
        List<Long> drinkID = new ArrayList<Long>();
        drinkID.add(Long.valueOf(1));
        String date = "2022-01-24 10:00";
        CalendarPostRequestDto dto = CalendarPostRequestDto.builder()
                .userID(userID).drinkID(drinkID).date(date).build();

        String object = objectMapper.writeValueAsString(dto);

        String url = "/v1/calendar/post";

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post(url).content(object)
                .contentType(MediaType.APPLICATION_JSON));

        actions.andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testGetList() throws Exception {
        Long userID = Long.valueOf(1);
        Integer month = 1;

        String url = "/v1/calendar";

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get(url).param("userID", String.valueOf(userID)).param("month",month.toString())
                .contentType(MediaType.APPLICATION_JSON));

        actions.andDo(print()).andExpect(status().isOk());
    }
}
