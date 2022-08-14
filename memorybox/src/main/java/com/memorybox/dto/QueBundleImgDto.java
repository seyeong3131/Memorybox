package com.memorybox.dto;

import com.memorybox.entity.QueBundleImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class QueBundleImgDto {
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;
    private static ModelMapper modelMapper = new ModelMapper();

    public static QueBundleImgDto of(QueBundleImg queBundleImg){
        return modelMapper.map(queBundleImg,QueBundleImgDto.class);}
    }
