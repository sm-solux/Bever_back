package com.example.bever.controller;

import com.example.bever.dto.RecipePostRequestDto;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@NoArgsConstructor
@SpringBootTest
@RunWith(SpringRunner.class)
public class RecipeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Recipe-post테스트")
    public void testPost() throws Exception {
        //userID, drinkID, recipeName, recipeContent, imageLink, time
        Long userID = Long.valueOf(3);
        Long drinkID = Long.valueOf(1);
        String title = "테스트1 제목";
        String content = "테스트1 내용";
        String imageLink = "테스트1 이미지";
        String date = "2022-01-24 10:00";

        RecipePostRequestDto dto = RecipePostRequestDto.builder()
                .writer(userID).drinkID(drinkID).title(title).content(content)
                .imageLink(imageLink).date(date).build();

        String url = "/v1/recipe/post";

        String object = objectMapper.writeValueAsString(dto);

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post(url).content(object)
                .contentType(MediaType.APPLICATION_JSON));

        actions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Recipe-list테스트")
    public void testList() throws Exception {
        String url = "/v1/recipe/list";
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get(url));

        actions.andDo(print()).andExpect(status().isOk());
    }

}
