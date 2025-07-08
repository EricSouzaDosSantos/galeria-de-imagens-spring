package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.auth;

import com.desafios.galeriaimagensspring.application.usecase.user.get.GetUserUseCase;
import com.desafios.galeriaimagensspring.core.gateways.AuthGateway;
import com.desafios.galeriaimagensspring.infrastructure.security.autentication.JwtTokenService;
import com.desafios.galeriaimagensspring.infrastructure.security.autentication.SpringSecurityAuthServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthConfig {

    @Bean
    public AuthGateway authGateway(AuthenticationManager authenticationManager,
                                   JwtTokenService jwtTokenService,
                                   PasswordEncoder passwordEncoder,
                                   GetUserUseCase getUserUseCase){
        return new SpringSecurityAuthServiceImpl(
                authenticationManager,
                jwtTokenService,
                passwordEncoder,
                getUserUseCase);
    }
}
