package com.memorybox.repository;

import com.memorybox.entity.QueBundleImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueBundleImgRepository extends JpaRepository<QueBundleImg,Long> {

//    QueBundleImg findByItemIdOrderByIdAsc(Long queBundleId);
//
//    QueBundleImg findByItemIdAndRepImgYn(Long itemId, String repImgYn);

}
