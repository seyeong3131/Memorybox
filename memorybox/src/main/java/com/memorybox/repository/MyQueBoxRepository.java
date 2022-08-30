package com.memorybox.repository;

import com.memorybox.entity.MyQueBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyQueBoxRepository extends JpaRepository<MyQueBox,Long> {
    MyQueBox findByMemberId(Long memberId);
}
