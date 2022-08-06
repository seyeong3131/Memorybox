package com.memorybox.dto;

import com.memorybox.entity.Question;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class QuestionFormDto {
    private Long id;

    @NotBlank(message = "문제 설명은 필수 입력값입니다.")
    private String queDetail;

    @NotBlank(message = "문제 뒷면 해설은 필수 입력값입니다.")
    private String queBackDetail;

    private static ModelMapper modelMapper = new ModelMapper();
    public Question createQuestion(){return modelMapper.map(this, Question.class);}
    public static QuestionFormDto of(Question question){
        return modelMapper.map(question, QuestionFormDto.class);
    }
}
