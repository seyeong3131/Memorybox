package com.memorybox.repository;

import com.memorybox.entity.Member;
import com.memorybox.entity.SaveQue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveQueRepository extends JpaRepository<SaveQue, Long> {
    SaveQue findByMemberId(Long memberId);


}
