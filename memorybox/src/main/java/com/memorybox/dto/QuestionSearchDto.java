package com.memorybox.dto;

import com.memorybox.entity.QueBundle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionSearchDto {

    private QueBundle searchQueBundle;

    private String searchDateType;

    private String searchBy;

    private String searchQuery="";
}
