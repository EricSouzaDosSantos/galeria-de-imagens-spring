package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.auth;

import com.desafios.galeriaimagensspring.application.usecase.auth.get.GetAuthenticateUserUseCase;
import com.desafios.galeriaimagensspring.application.usecase.auth.get.GetAuthenticateUserUseCaseImpl;
import com.desafios.galeriaimagensspring.application.usecase.auth.validation.ValidateUserAuthorizationUseCase;
import com.desafios.galeriaimagensspring.application.usecase.auth.validation.ValidateUserAuthorizationUseCaseImpl;
import com.desafios.galeriaimagensspring.core.gateways.AuthGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCaseConfig {
    @Bean
    public GetAuthenticateUserUseCase getAuthenticateUserUseCase(AuthGateway authGateway) {
        return new GetAuthenticateUserUseCaseImpl(authGateway);
    }

    @Bean
    public ValidateUserAuthorizationUseCase validateUserAuthorizationUseCase(AuthGateway authGateway) {
        return new ValidateUserAuthorizationUseCaseImpl(authGateway);
    }
}
