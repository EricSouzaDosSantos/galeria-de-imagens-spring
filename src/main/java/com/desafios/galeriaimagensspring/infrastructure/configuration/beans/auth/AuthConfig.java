package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.auth;

import com.desafios.galeriaimagensspring.application.usecase.user.get.GetUserUseCase;
import com.desafios.galeriaimagensspring.core.gateways.AuthGateway;
import com.desafios.galeriaimagensspring.infrastructure.security.autentication.SpringSecurityAuthServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    public AuthGateway authGateway(GetUserUseCase getUserUseCase){
        return new SpringSecurityAuthServiceImpl(getUserUseCase);
    }
}
