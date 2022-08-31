package com.memorybox.repository;

import com.memorybox.dto.MyQueBoxDetailDto;
import com.memorybox.dto.SaveQueAlertDto;
import com.memorybox.dto.SaveQueBookDto;
import com.memorybox.entity.Member;
import com.memorybox.entity.SaveQue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaveQueRepository extends JpaRepository<SaveQue, Long> {

    SaveQue findBySaveQueBookIdAndQuestionId(Long saveQueBookId, Long QuestionId);
    List<SaveQue> findBySaveQueBookId(Long saveQueBookId);

    @Query("select new com.memorybox.dto.SaveQueBookDto(ci.id) " +
            "from SaveQue ci "+
            "where ci.saveQueBook.id = :saveQueBookId "
            )
    List<SaveQueBookDto> findSaveQueBookDtoList(Long saveQueBookId);

}
