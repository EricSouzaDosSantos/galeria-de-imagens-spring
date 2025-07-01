package com.desafios.galeriaimagensspring.application.usecase.user.save;

import com.desafios.galeriaimagensspring.core.model.User;
import com.desafios.galeriaimagensspring.core.gateways.UserGateway;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserGateway userGateway;

    public CreateUserUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void execute(User user) {
        userGateway.save(user);
    }
}