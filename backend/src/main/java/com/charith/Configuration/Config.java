//package com.charith.Configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import java.util.List;
//
//@Configuration
//public class Config {
//
//    private CorsConfigurationSource corsConfigurationSource() {
//        return request -> {
//            CorsConfiguration cfg = new CorsConfiguration();
//            cfg.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:3000"));
//            cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//            cfg.setAllowedHeaders(List.of("*"));
//            cfg.setAllowCredentials(true);
//            cfg.setMaxAge(3600L);
//            return cfg;
//        };
//    }
//}
