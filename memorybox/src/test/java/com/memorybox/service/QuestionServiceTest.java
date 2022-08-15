package com.memorybox.service;

import com.memorybox.dto.QuestionFormDto;
import com.memorybox.entity.Question;
import com.memorybox.repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class QuestionServiceTest {
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionRepository questionRepository;

    @Test
    @DisplayName("문제 등록 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void saveQuestion() throws Exception{
        QuestionFormDto questionFormDto = new QuestionFormDto();
        questionFormDto.setQueDetail("테스트 문제입니다.");
        questionFormDto.setQueBackDetail("테스트 문제 해설입니다.");

        Long queId = questionService.saveQuestion(questionFormDto);

        Question question = questionRepository.findById(queId).orElseThrow(EntityExistsException::new);
        assertEquals(questionFormDto.getQueDetail(), question.getQueDetail());
        assertEquals(questionFormDto.getQueBackDetail(), question.getQueBackDetail());
    }

}