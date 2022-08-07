package com.memorybox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionSearchDto {
    private String searchDateType;

    private String searchBy;

    private String searchQuery="";
}
