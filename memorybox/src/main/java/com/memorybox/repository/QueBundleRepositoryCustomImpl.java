package com.memorybox.repository;

import com.memorybox.dto.QueBundleSearchDto;
import com.memorybox.dto.QuestionSearchDto;
import com.memorybox.entity.QQueBundle;
import com.memorybox.entity.QQuestion;
import com.memorybox.entity.QueBundle;
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

public class QueBundleRepositoryCustomImpl implements QueBundleRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public QueBundleRepositoryCustomImpl (EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    public BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if(StringUtils.equals("queBundleNm", searchBy)) {
            return QQueBundle.queBundle.queBundleNm.like("%"+searchQuery+"%");
        } else if(StringUtils.equals("createdBy", searchBy)) {
            return QQueBundle.queBundle.createdBy.like("%"+searchQuery+"%");
        }
        return null;
    }

    @Override
    public Page<QueBundle> getAdminQueBundlePage(QueBundleSearchDto queBundleSearchDto, Pageable pageable) {
        QueryResults<QueBundle> results = queryFactory.selectFrom(QQueBundle.queBundle).
                where(searchByLike(queBundleSearchDto.getSearchBy(), queBundleSearchDto.getSearchQuery()))
                .orderBy(QQueBundle.queBundle.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize()).fetchResults();
        List<QueBundle> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
