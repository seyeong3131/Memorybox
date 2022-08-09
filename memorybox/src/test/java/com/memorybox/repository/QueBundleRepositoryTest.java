package com.memorybox.repository;

import com.memorybox.constant.QCategory;
import com.memorybox.entity.QueBundle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class QueBundleRepositoryTest {

    @Autowired
    QueBundleRepository queBundleRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("문제지 저장 테스트")
    public void createQueBundleTest(){
        QueBundle queBundle = new QueBundle();
        queBundle.setQueBundleNm("테스트 문제지");
        queBundle.setQCategory(QCategory.ENGLISH);
        QueBundle savedQueBundle = queBundleRepository.save(queBundle);
        System.out.println(savedQueBundle.toString());
    }

}