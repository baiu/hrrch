package com.baiu.hrrch.config;

import com.baiu.hrrch.exception.NotAuthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final AuthModuleConfig authModuleConfig;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AuthModuleConfig authModuleConfig) {
        super(authenticationManager);
        this.authModuleConfig = authModuleConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(authModuleConfig.getTokenHeader());
        if (StringUtils.isNotEmpty(token) && token.startsWith(authModuleConfig.getTokenPrefix())) {
            try {

                Jws<Claims> parsedToken = Jwts.parser()
                        .setSigningKey(authModuleConfig.getTokenSecret().getBytes())
                        .parseClaimsJws(token.replace(authModuleConfig.getTokenPrefix(), ""));

                String username = parsedToken
                        .getBody()
                        .getSubject();

                List<SimpleGrantedAuthority> authorities = ((List<?>) parsedToken.getBody()
                        .get("rol")).stream()
                        .map(authority -> new SimpleGrantedAuthority((String) authority))
                        .collect(Collectors.toList());

                if (StringUtils.isNotEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                }
            } catch (JwtException | IllegalArgumentException e) {
                throw new NotAuthorizedException(e);
            }
        }

        return null;
    }
}
