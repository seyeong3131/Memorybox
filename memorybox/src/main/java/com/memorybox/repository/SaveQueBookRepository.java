package com.memorybox.repository;

import com.memorybox.entity.Member;
import com.memorybox.entity.SaveQueBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveQueBookRepository extends JpaRepository<SaveQueBook, Long> {
    Member findBySaveQueBookIdAndQuestionId(Long QuestionId);
}
