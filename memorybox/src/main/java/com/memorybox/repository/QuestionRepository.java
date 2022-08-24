package com.memorybox.repository;

import com.memorybox.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuerydslPredicateExecutor<Question>, QuestionRepositoryCustom {
    @Query("select * from question where id = :id")
    Question findQueById(Long id);
}
