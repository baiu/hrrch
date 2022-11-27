package com.baiu.hrrch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthModuleConfig {

    @Value("${token.secret}")
    private String tokenSecret;

    @Value("${token.header}")
    private String tokenHeader;

    @Value("${token.prefix}")
    private String tokenPrefix;

    @Value("${role.hierarchy}")
    private String hierarchy;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }
}
