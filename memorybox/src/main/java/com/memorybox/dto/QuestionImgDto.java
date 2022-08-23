package com.memorybox.dto;

import com.memorybox.entity.QueBundleImg;
import com.memorybox.entity.QuestionImg;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class QuestionImgDto {
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;
    private static ModelMapper modelMapper = new ModelMapper();


    public static QuestionImgDto of(QuestionImg questionImg){
        return modelMapper.map(questionImg,QuestionImgDto.class);}
}
