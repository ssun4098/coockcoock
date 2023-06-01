package com.coockcoock.shop.member.controller;

import com.coockcoock.shop.member.dto.*;
import com.coockcoock.shop.member.service.CommandMemberService;
import com.coockcoock.shop.utils.CookieUtil;
import com.coockcoock.shop.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CommandMemberController.class)
class CommandMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CommandMemberService commandMemberService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("checkLoginIdSuccess 메서드 성공")
    void checkLoginIdSuccess() throws Exception {
        //given
        Mockito.when(commandMemberService.checkLoginId("loginId")).thenReturn(new CheckLoginIdResponseDto(true));

        //when
        ResultActions resultActions = mockMvc.perform(get("/v1/members/duplicability")
                .param("loginId", "loginId"));

        //then
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.check", equalTo(true)))
                .andExpect(jsonPath("$.success", equalTo(true)))
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 로그인 아이디의 길이가 0일 경우 ValidException")
    void createMemberThrowsValidLoginIdLengthLessThenZero() throws Exception {
        //given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto("1", "password");

        ResultActions resultActions = mockMvc.perform(post("/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(createMemberRequestDto)));

        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("회원가입 로그인 아이디의 길이가 12를 넘어갈 경우 ValidException")
    void createMemberThrowsValidLoginIdLengthGreaterThan12() throws Exception {
        //given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto("1111111111111", "password");

        ResultActions resultActions = mockMvc.perform(post("/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(createMemberRequestDto)));

        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("회원가입 비밀번호의 길이가 0일 경우 ValidException")
    void createMemberThrowsValidPasswordLengthLessThenZero() throws Exception {
        //given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto("111", "");

        ResultActions resultActions = mockMvc.perform(post("/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(createMemberRequestDto)));

        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("회원가입 비밀번호의 길이가 12를 넘어갈 경우 ValidException")
    void createMemberThrowsValidPasswordLengthGreaterThan12() throws Exception {
        //given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto("111", "password12345");

        ResultActions resultActions = mockMvc.perform(post("/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(createMemberRequestDto)));

        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("회원가입 성공")
    void createMemberSuccess() throws Exception {
        //given
        LocalDate signUpDate = LocalDate.of(2000, 10, 10);
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto("loginId", "password");
        CreateMemberResponseDto createMemberResponseDto = new CreateMemberResponseDto(signUpDate);

        Mockito.when(commandMemberService.create(any())).thenReturn(createMemberResponseDto);

        //when
        ResultActions resultActions = mockMvc.perform(post("/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(createMemberRequestDto)));

        //then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success", equalTo(true)))
                .andExpect(jsonPath("$.data.signUpDate", equalTo(signUpDate.toString())));
    }

    @Test
    @DisplayName("회원 정보 수정 시 loginId 길이가 2보다 작을 경우 Exception")
    void updateMemberThrowsValidLoginIdLengthLessThen2() throws Exception {
            //given
            UpdateMemberRequestDto requestDto = new UpdateMemberRequestDto("1", "password");

            //when
            ResultActions resultActions = mockMvc.perform(put("/v1/members")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(requestDto)));

            //then
            resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("회원 정보 수정 시 loginId 길이가 12보다 클 경우 Exception")
    void updateMemberThrowsValidLoginIdLengthLessThen12() throws Exception {
        //given
        UpdateMemberRequestDto requestDto = new UpdateMemberRequestDto("1111111111111", "password");

        //when
        ResultActions resultActions = mockMvc.perform(put("/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(requestDto)));

        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("회원 정보 수정 시 Password 길이가 2보다 작을 경우 Exception")
    void updateMemberThrowsValidPasswordLengthLessThen2() throws Exception {
        //given
        UpdateMemberRequestDto requestDto = new UpdateMemberRequestDto("loginId", "1");

        //when
        ResultActions resultActions = mockMvc.perform(put("/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(requestDto)));

        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("회원 정보 수정 시 Password 길이가 12보다 클 경우 Exception")
    void updateMemberThrowsValidPasswordLengthGreaterThen12() throws Exception {
        //given
        UpdateMemberRequestDto requestDto = new UpdateMemberRequestDto("loginId", "password12345");

        //when
        ResultActions resultActions = mockMvc.perform(put("/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(requestDto)));

        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("회원 정보 수정 성공")
    void updateSuccess() throws Exception {
        //given
        LocalDate signUpDate = LocalDate.of(2000, 10, 10);
        UpdateMemberRequestDto requestDto = new UpdateMemberRequestDto("loginId", "password");
        UpdateMemberResponseDto responseDto = new UpdateMemberResponseDto(signUpDate);

        Mockito.when(commandMemberService.update(any())).thenReturn(responseDto);

        //when
        ResultActions resultActions = mockMvc.perform(put("/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(requestDto)));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", equalTo(true)))
                .andExpect(jsonPath("$.data.updateDate", equalTo(signUpDate.toString())));
    }

    @Test
    @DisplayName("회원 삭제 시 loginId 길이가 2보다 작을 경우 ValidException")
    void logicDeleteThrowsLessThan2() throws Exception {
        //given, when
        ResultActions resultActions = mockMvc.perform(delete("/v1/members").param("loginId", "1"));

        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("회원 삭제 시 loginId 길이가 12보다 클 경우 ValidException")
    void logicDeleteThrowsGreaterThan12() throws Exception {
        //given, when
        ResultActions resultActions = mockMvc.perform(delete("/v1/members").param("loginId", "loginId123456"));

        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("회원 삭제 성공")
    void logicDeleteSuccess() throws Exception {
        //given, when
        ResultActions resultActions = mockMvc.perform(delete("/v1/members").param("loginId", "loginId"));

        //then
        resultActions.andExpect(status().isNoContent());
        verify(commandMemberService, atLeastOnce()).logicalDelete("loginId");
    }
}