package com.memorybox.entity;

import com.memorybox.dto.QuestionFormDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Question")
@Getter
@Setter
public class Question extends BaseEntity{
    @Id
    @Column(name="que_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String queDetail;

    @Column(nullable = false)
    private String queBackDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "que_bundle_id")
    private QueBundle queBundle;

    public void updateQuestion (QuestionFormDto questionFormDto){
        this.queDetail = questionFormDto.getQueDetail();
        this.queBackDetail = questionFormDto.getQueBackDetail();
    }
}
