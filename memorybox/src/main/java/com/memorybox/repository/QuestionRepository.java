package com.memorybox.repository;

import com.memorybox.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuerydslPredicateExecutor<Question>, QuestionRepositoryCustom {
    Question findOneById(Long id);
}
