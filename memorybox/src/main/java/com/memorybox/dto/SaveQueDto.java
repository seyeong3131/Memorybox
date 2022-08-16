package com.memorybox.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveQueDto {
    @NotNull(message = "문제 아이디는 필수 입력 값 입니다.")
    private Long QuestionId;

}
