package com.memorybox.repository;


import com.memorybox.entity.QuestionImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionImgRepository extends JpaRepository<QuestionImg,Long> {
    QuestionImg findByQuestionId(Long questionId);
}
