package com.memorybox.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.memorybox.dto.QMainQueBundleDto is a Querydsl Projection type for MainQueBundleDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainQueBundleDto extends ConstructorExpression<MainQueBundleDto> {

    private static final long serialVersionUID = -1766555339L;

    public QMainQueBundleDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> queBundleNm, com.querydsl.core.types.Expression<com.memorybox.constant.QCategory> qCategory, com.querydsl.core.types.Expression<String> imgUrl) {
        super(MainQueBundleDto.class, new Class<?>[]{long.class, String.class, com.memorybox.constant.QCategory.class, String.class}, id, queBundleNm, qCategory, imgUrl);
    }

}

