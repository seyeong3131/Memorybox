package com.memorybox.repository;

import com.memorybox.dto.MyQueBoxDetailDto;
import com.memorybox.entity.MyQueBoxItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyQueBoxItemRepository extends JpaRepository<MyQueBoxItem,Long> {

    MyQueBoxItem findByMyQueBoxIdAndQuestionId(Long myQueBoxId, Long questionId);

    @Query("select new com.memorybox.dto.MyQueBoxDetailDto(ci.id, i.queDetail, im.imgUrl) " +
            "from MyQueBoxItem ci, QuestionImg im "+
            "join ci.question i " +
            "where ci.myQueBox.id = :myQueBoxId " +
            "and im.question.id = ci.question.id ")
    List<MyQueBoxDetailDto> findMyQueBoxDetailDtoList(Long myQueBoxId);

}
