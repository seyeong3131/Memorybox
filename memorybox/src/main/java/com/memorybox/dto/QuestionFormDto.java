package com.memorybox.dto;

import com.memorybox.entity.QueBundle;
import com.memorybox.entity.Question;
import com.memorybox.repository.QueBundleRepository;
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
    private String queBundleNm;
    public Question createQuestion(QueBundleRepository queBundleRepository){
        Question question = new Question();
        question.setQueDetail(this.queDetail);
        question.setQueBackDetail(this.queBackDetail);
        question.setQueBundle(queBundleRepository.findByQueBundleNm(this.queBundleNm));
        return question;
    }
    public static QuestionFormDto of(Question question){
        QuestionFormDto questionFormDto = new QuestionFormDto();
        questionFormDto.setId(question.getId());
        questionFormDto.setQueDetail(question.getQueDetail());
        questionFormDto.setQueBackDetail(question.getQueBackDetail());
        questionFormDto.setQueBundleNm(question.getQueBundle().getQueBundleNm());
        return questionFormDto;
    }
}
