package com.memorybox.dto;

import com.memorybox.entity.QueBundle;
import com.memorybox.entity.Question;
import com.memorybox.repository.QuestionImgRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDto {
    private Long id;
    private String queDetail;
    private String queBackDetail;
    private String queImgUrl;

    public QuestionDto(Question question, QuestionImgRepository questionImgRepository){
        this.id = question.getId();
        this.queDetail = question.getQueDetail();
        this.queBackDetail = question.getQueBackDetail();
        this.queImgUrl = questionImgRepository.findImgUrlByQuestionId(question.getId());
    }
}
