package com.memorybox.dto;

import com.memorybox.entity.QueBundle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDto {
    private Long id;
    private String queDetail;
    private String queBackDetail;
    private QueBundleDto queBundleDto;
}
