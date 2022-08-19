package com.memorybox.repository;

import com.memorybox.constant.QCategory;
import com.memorybox.dto.MainQueBundleDto;
import com.memorybox.dto.QMainQueBundleDto;
import com.memorybox.dto.QueBundleSearchDto;
import com.memorybox.dto.QuestionSearchDto;
import com.memorybox.entity.*;
import com.querydsl.core.QueryResults;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class QueBundleRepositoryCustomImpl implements QueBundleRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public QueBundleRepositoryCustomImpl (EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    public BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if(StringUtils.equals("queBundleNm", searchBy)) {
            return QQueBundle.queBundle.queBundleNm.like("%"+searchQuery+"%");
        } else if(StringUtils.equals("qCategory", searchBy)) {
            return QQueBundle.queBundle.createdBy.like("%"+searchQuery+"%");
        }
        return null;
    }

    private BooleanExpression searchCategory(QCategory searchCategory){
        return searchCategory == null ?
                null : QQueBundle.queBundle.qCategory.eq(searchCategory);
        //ItemSellStatus null이면 null 리턴 null 아니면 SELL, SOLD 둘중 하나 리턴
    }


    @Override
    public Page<QueBundle> getAdminQueBundlePage(QueBundleSearchDto queBundleSearchDto, Pageable pageable) {
        QueryResults<QueBundle> results = queryFactory.selectFrom(QQueBundle.queBundle).
                where(searchByLike(queBundleSearchDto.getSearchBy(), queBundleSearchDto.getSearchQuery()),
                        regDtsAfter(queBundleSearchDto.getSearchDateType()),
                        searchCategory(queBundleSearchDto.getSearchCategory()))
                .orderBy(QQueBundle.queBundle.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize()).fetchResults();
        List<QueBundle> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression queBundleNmLike(String searchQuery){
        return StringUtils.isEmpty(searchQuery) ? null : QQueBundle.queBundle.queBundleNm.like("%"+searchQuery+"%");
    }

    @Override
    public Page<MainQueBundleDto> getMainQueBundlePage(QueBundleSearchDto queBundleSearchDto, Pageable pageable){
        QQueBundle queBundle = QQueBundle.queBundle;
        QQueBundleImg queBundleImg = QQueBundleImg.queBundleImg;

        //QMainItemDto @QueryProjection을 하용하면 DTO로 바로 조회 가능
        QueryResults<MainQueBundleDto> results = queryFactory.select(new QMainQueBundleDto(queBundle.id, queBundle.queBundleNm,
                        queBundle.qCategory, queBundleImg.imgUrl))
                // join 내부조인 .repImgYn.eq("Y") 대표이미지만 가져온다.
                 .from(queBundleImg).join(queBundleImg.queBundle, queBundle)
                .where(queBundleNmLike(queBundleSearchDto.getSearchQuery()),
                        searchCategory(queBundleSearchDto.getSearchCategory())
                        )
                .orderBy(queBundle.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetchResults();
        List<MainQueBundleDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable,total);
    }



    private  BooleanExpression regDtsAfter(String searchDateType){ // all, 1d, 1w, 1m 6m
        LocalDateTime dateTime = LocalDateTime.now(); // 현재시간을 추출해서 변수에 대입

        if(StringUtils.equals("all",searchDateType) || searchDateType == null){
            return null;
        }else if(StringUtils.equals("1d",searchDateType)){
            dateTime = dateTime.minusDays(1);
        }else if(StringUtils.equals("1w",searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        }else if(StringUtils.equals("1m",searchDateType)){
            dateTime = dateTime.minusMonths(1);
        }else if(StringUtils.equals("6m",searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }
        return QQueBundle.queBundle.regTime.after(dateTime);
        //dateTime을 시간에 맞게 세팅 후 시간에 맞는 등록된 상품이 조회하도록 조건값 반환
    }
}
