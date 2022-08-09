package com.memorybox.entity;

import com.memorybox.constant.QCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name="que_bundle")
public class QueBundle {

    @Id
    @Column(name="que_bundle_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String queBundleNm; // 문제지 이름

    @Enumerated(EnumType.STRING)
    private QCategory qCategory; // 카테고리

    @OneToMany
    @JoinColumn(name = "que_id")
    private List<Question> questions;

}

