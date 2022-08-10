package com.memorybox.entity;

import com.memorybox.constant.QCategory;
import com.memorybox.dto.QueBundleFormDto;
import com.memorybox.dto.QuestionFormDto;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="que_bundle")

public class QueBundle extends BaseEntity{


    @Id
    @Column(name="que_bundle_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String queBundleNm; // 문제지 이름

    @Enumerated(EnumType.STRING)
    private QCategory qCategory; // 카테고리


    public void updateQueBundle (QueBundleFormDto queBundleFormDto){
        this.queBundleNm = queBundleFormDto.getQueBundleNm();
        this.qCategory = queBundleFormDto.getQCategory();
    }


}

