package com.memorybox.repository;

import com.memorybox.entity.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class QuestionRepositoryTest {

    @Autowired
    QuestionRepository questionRepository;

    @Test
    @DisplayName("문제 저장 테스트")
    public void createQueTest(){
        String queDetail = "문제 설명입니다.";
        String queBackDetail = "문제 뒷면 해설입니다.";

        Question question = new Question();
        question.setQueDetail(queDetail);
        question.setQueBackDetail(queBackDetail);
        Question savedQue = questionRepository.save(question);

        assertThat(savedQue.getQueDetail()).isEqualTo(queDetail);
        assertThat(savedQue.getQueBackDetail()).isEqualTo(queBackDetail);
    }

    public void createQueList(){
        for (int i=0; i<5; i++){
            Question question = new Question();
            question.setQueDetail("설명"+i);
            question.setQueBackDetail("해설"+i);
            Question saveQue = questionRepository.save(question);
        }
    }
}