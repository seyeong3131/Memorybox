package com.memorybox.service;

import com.memorybox.dto.QuestionFormDto;
import com.memorybox.dto.QuestionSearchDto;
import com.memorybox.entity.Question;
import com.memorybox.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    QuestionRepository questionRepository;

    public Long saveQuestion(QuestionFormDto questionFormDto) throws Exception{
        Question question = questionFormDto.createQuestion();
        questionRepository.save(question);

        return question.getId();
    }

    @Transactional(readOnly = true)
    public QuestionFormDto getQuestionDto(Long QuestionId){
        Question question = questionRepository.findById(QuestionId).orElseThrow(EntityNotFoundException::new);
        QuestionFormDto questionFormDto = QuestionFormDto.of(question);
        return questionFormDto;
    }

    public Long updateQuestion(QuestionFormDto questionFormDto) throws Exception{
        Question question = questionRepository.findById(questionFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        question.updateQuestion(questionFormDto);

        return question.getId();
    }

    @Transactional(readOnly = true)
    public Page<Question> getAdminQuestionPage(QuestionSearchDto questionSearchDto, Pageable pageable){
        return questionRepository.getAdminQuestionPage(questionSearchDto, pageable);
    }
}
