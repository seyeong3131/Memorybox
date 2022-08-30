package com.memorybox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyQueBoxDetailDto {

    private Long myQueBoxItemId;

    private String queDetail;

    private String imgUrl;

    public MyQueBoxDetailDto(Long myQueBoxItemId,String queDetail, String imgUrl){
        this.myQueBoxItemId = myQueBoxItemId;
        this.queDetail = queDetail;
        this.imgUrl = imgUrl;
    }
}
