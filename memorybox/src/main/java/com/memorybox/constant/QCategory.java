package com.memorybox.constant;

import lombok.Getter;

@Getter
public enum QCategory {

    KOREAN("국어"), ENGLISH("영어"), MATH("수학");

    private final String description;

    QCategory(String description){
        this.description = description;
    }
}
