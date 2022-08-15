package com.memorybox.repository;

import com.memorybox.constant.Role;
import com.memorybox.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);

    Member findByRole(Role role);
}
