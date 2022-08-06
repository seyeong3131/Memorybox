package com.memorybox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // Auditing 기능 활성화
public class AuditConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl(); // Bean 객체에 등록 AuditorAware<String>(자료형)
    }
}
