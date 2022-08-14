package com.memorybox.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "que_bundle_img")
@Getter
@Setter
public class QueBundleImg extends BaseEntity{
    @Id
    @Column(name = "que_bundle_img_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String imgName; //이미지 파일명
    private String oriImgName; // 원본 이미지 파일명
    private String imgUrl;// 이미지 조회 경로

    private String repImgYn; //대표이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "que_bundle_id")
    private QueBundle queBundle;

    public void updateQueBundleImg(String oriImgName, String imgName, String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
