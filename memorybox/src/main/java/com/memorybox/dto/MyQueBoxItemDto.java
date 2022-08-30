package com.memorybox.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MyQueBoxItemDto {
    @NotNull(message = "문제 아이디는 필수")
    private Long questionId;

}
