package com.memorybox.repository;

import com.memorybox.dto.SaveQueAlertDto;
import com.memorybox.entity.Member;
import com.memorybox.entity.SaveQue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaveQueRepository extends JpaRepository<SaveQue, Long> {

    SaveQue findBySaveQueBookIdAndQuestionId(Long saveQueBookId, Long QuestionId);
    List<SaveQue> findBySaveQueBookId(Long saveQueBookId);
    Long findQueIdById(Long id);

}
