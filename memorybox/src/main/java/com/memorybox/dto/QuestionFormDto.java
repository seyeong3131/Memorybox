package com.memorybox.dto;

import com.memorybox.entity.Question;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class QuestionFormDto {
    private Long id;

    @NotBlank(message = "문제 설명은 필수 입력값입니다.")
    private String queDetail;

    @NotBlank(message = "문제 뒷면 해설은 필수 입력값입니다.")
    private String queBackDetail;

    @NotNull(message = "문제지 분류는 필수 입력값읍니다.")
    private QueBundleDto queBundleDto;

    private static ModelMapper modelMapper = new ModelMapper();
    public Question createQuestion(){return modelMapper.map(this, Question.class);}
    public static QuestionFormDto of(Question question){
        return modelMapper.map(question, QuestionFormDto.class);
    }
}
