package com.memorybox.repository;

import com.memorybox.dto.QuestionSearchDto;
import com.memorybox.entity.QQuestion;
import com.memorybox.entity.Question;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom{
    private JPAQueryFactory queryFactory;

    public QuestionRepositoryCustomImpl (EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    public BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if(StringUtils.equals("queDetail", searchBy)) {
            return QQuestion.question.queDetail.like("%"+searchQuery+"%");
        } else if(StringUtils.equals("createdBy", searchBy)) {
            return QQuestion.question.createdBy.like("%"+searchBy+"%");
        }
        return null;
    }

    @Override
    public Page<Question> getAdminQuestionPage(QuestionSearchDto questionSearchDto, Pageable pageable) {
        QueryResults<Question> results = queryFactory.selectFrom(QQuestion.question).
                where(searchByLike(questionSearchDto.getSearchBy(), questionSearchDto.getSearchQuery()))
                .orderBy(QQuestion.question.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize()).fetchResults();
        List<Question> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
