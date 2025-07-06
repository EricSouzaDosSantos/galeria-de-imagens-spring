package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.user;

import com.desafios.galeriaimagensspring.application.service.UserService;
import com.desafios.galeriaimagensspring.application.usecase.auth.authenticate.AuthenticateUserUseCase;
import com.desafios.galeriaimagensspring.application.usecase.user.save.SaveUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {

    @Bean
    public UserService userService(AuthenticateUserUseCase authenticateUserUseCase,
                                   SaveUserUseCase saveUserUseCase) {
        return new UserService(authenticateUserUseCase, saveUserUseCase);
    }
}
