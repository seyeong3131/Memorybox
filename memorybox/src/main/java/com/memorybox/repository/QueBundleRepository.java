package com.memorybox.repository;

import com.memorybox.entity.QueBundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueBundleRepository extends JpaRepository<QueBundle, Long>{
}
