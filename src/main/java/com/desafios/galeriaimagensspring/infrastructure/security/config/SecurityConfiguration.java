package com.desafios.galeriaimagensspring.infrastructure.security.config;

import com.desafios.galeriaimagensspring.infrastructure.security.filters.UserAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/auth/login",
            "/auth/register",
            "/auth/refresh-token",
            "/auth/forgot-password",
            "/auth/reset-password",
            "/auth/verify-email",
            "/auth/verify-email/{token}",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/docs",
            "/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/webjars/**"
    };




    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/api/v1/images/**",
            "/api/v1/albums/**",
    };

    public static final String[] ENDPOINTS_VIEWER = {
            ""
    };

    public static final String[] ENDPOINTS_USER = {
            ""
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, UserAuthenticationFilter userAuthenticationFilter) throws Exception{

        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
//                                .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
//                                .requestMatchers(ENDPOINTS_VIEWER).hasAnyRole("VIEWER")
//                                .requestMatchers(ENDPOINTS_USER).hasAnyRole("USER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
 }
