package com.memorybox.repository;

import com.memorybox.dto.QuestionSearchDto;
import com.memorybox.entity.QueBundle;
import com.memorybox.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionRepositoryCustom {
    Page<Question> getAdminQuestionPage(QuestionSearchDto questionSearchDto, Pageable pageable);
    Page<Question> getsearchQueBundle(Long queBundle_id, Pageable pageable);
}
