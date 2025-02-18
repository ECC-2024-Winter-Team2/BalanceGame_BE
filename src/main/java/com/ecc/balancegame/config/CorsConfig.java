package com.ecc.balancegame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 엔드포인트 허용
                        .allowedOrigins(
                                "http://localhost:3003",
                                "http://ec2-43-200-237-253.ap-northeast-2.compute.amazonaws.com"
                        ) // 허용할 도메인 추가
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
