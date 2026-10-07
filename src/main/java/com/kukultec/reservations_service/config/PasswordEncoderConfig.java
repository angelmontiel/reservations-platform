package com.kukultec.reservations_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Solo el encoder, adelantado de la Fase 2 (JWT/Spring Security completos).
 * Necesitas la dependencia spring-boot-starter-security en el pom.xml para
 * que BCryptPasswordEncoder esté disponible.
 */
@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}