package com.coockcoock.shop.ingredient.controller;

import com.coockcoock.shop.ingredient.dto.IngredientCreateRequestDto;
import com.coockcoock.shop.ingredient.dto.IngredientCreateResponseDto;
import com.coockcoock.shop.ingredient.dto.IngredientDeleteRequestDto;
import com.coockcoock.shop.ingredient.service.CommandIngredientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CommandIngredientController.class)
class CommandIngredientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CommandIngredientService commandIngredientService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createTest() throws Exception {
        //given
        LocalDateTime now = LocalDateTime.of(2011, 10, 10, 1, 1, 0);
        IngredientCreateRequestDto ingredientCreateRequestDto = new IngredientCreateRequestDto("test");
        Mockito.when(commandIngredientService.create(any()))
                .thenReturn(new IngredientCreateResponseDto(now));
        String content1 = objectMapper.writeValueAsString(ingredientCreateRequestDto);
        mockMvc.perform(post("/v1/ingredients")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success", equalTo(true)))
                .andExpect(jsonPath("$.data.createDate", equalTo(now.format(DateTimeFormatter.ISO_DATE_TIME))))
                .andDo(print());
        verify(commandIngredientService, atLeastOnce()).create(any());
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/v1/ingredients")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(new IngredientDeleteRequestDto(1L))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", equalTo(true)));
    }
}