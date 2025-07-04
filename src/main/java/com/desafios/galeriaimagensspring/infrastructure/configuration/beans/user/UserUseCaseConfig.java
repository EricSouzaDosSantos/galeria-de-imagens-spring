package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.user;

import com.desafios.galeriaimagensspring.application.usecase.user.get.GetUserUseCase;
import com.desafios.galeriaimagensspring.application.usecase.user.get.GetUserUseCaseImpl;
import com.desafios.galeriaimagensspring.application.usecase.user.save.SaveUserUseCase;
import com.desafios.galeriaimagensspring.application.usecase.user.save.SaveUserUseCaseImpl;
import com.desafios.galeriaimagensspring.core.gateways.AuthGateway;
import com.desafios.galeriaimagensspring.core.gateways.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfig {

    @Bean
    public GetUserUseCase getUserUseCase(UserGateway userGateway){
        return new GetUserUseCaseImpl(userGateway);
    }

    @Bean
    public SaveUserUseCase saveUserUseCase(UserGateway userGateway,
                                           AuthGateway authGateway) {
        return new SaveUserUseCaseImpl(userGateway, authGateway);
    }


}
