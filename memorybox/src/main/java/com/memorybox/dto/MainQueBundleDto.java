package com.memorybox.dto;

import com.memorybox.constant.QCategory;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainQueBundleDto {
    private Long id;
    private String queBundleNm;
    private QCategory qCategory;

    @QueryProjection //Querydsl 결과 조회 시 MainItemDto 객체로 바로 오도록  활용
    public MainQueBundleDto(Long id, String queBundleNm, QCategory qCategory) {
        this.id = id;
        this.queBundleNm = queBundleNm;
        this.qCategory = qCategory;
    }
}
