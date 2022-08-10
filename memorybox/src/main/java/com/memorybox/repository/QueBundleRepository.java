package com.memorybox.repository;

import com.memorybox.entity.QueBundle;
import com.memorybox.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueBundleRepository extends JpaRepository<QueBundle, Long>, QuerydslPredicateExecutor<QueBundle>, QueBundleRepositoryCustom{
    QueBundle findByQueBundleNm(String queBundleNm);

    @Query("select queBundleNm from QueBundle")
    List<String> findAllQueBundleNm();
}
