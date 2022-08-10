package com.memorybox.repository;

import com.memorybox.dto.QueBundleSearchDto;
import com.memorybox.entity.QueBundle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueBundleRepositoryCustom {

        Page<QueBundle> getAdminQueBundlePage(QueBundleSearchDto queBundleSearchDto, Pageable pageable);

}
