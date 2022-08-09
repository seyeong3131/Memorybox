package com.memorybox.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class QuestionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("상품 등록 권환 테스트")
    @WithMockUser(username = "amdin", roles = "ADMIN")
    void itemFormTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/question/new"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("상품 등록 페이지 일반 회원 접근 테스트")
    @WithMockUser(username = "user", roles = "USER")
    public void itemForNotAdminTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/question/new"))
                .andDo(print())
                .andExpect(status().isForbidden());
        // 응답이 Forbidden이 발생하면 => 권한 없는 사용자 접근 403 error
    }

}