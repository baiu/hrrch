package com.baiu.hrrch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import java.util.ArrayList;
import java.util.List;

@EnableGlobalMethodSecurity(
        securedEnabled = true
)
@Configuration
public class RoleConfig extends GlobalMethodSecurityConfiguration {

    private final AuthModuleConfig authModuleConfig;

    public RoleConfig(AuthModuleConfig authModuleConfig) {
        this.authModuleConfig = authModuleConfig;
    }

    @Override
    protected AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
        decisionVoters.add(new AuthenticatedVoter());
        decisionVoters.add(roleHierarchyVoter());
        return new AffirmativeBased(decisionVoters);
    }

    private RoleHierarchyVoter roleHierarchyVoter() {
        return new RoleHierarchyVoter(authModuleConfig.roleHierarchy());
    }
}
