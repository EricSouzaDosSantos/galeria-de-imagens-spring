package com.desafios.galeriaimagensspring.application.usecase.user.get;

import com.desafios.galeriaimagensspring.core.gateways.UserGateway;
import com.desafios.galeriaimagensspring.core.model.User;

import java.util.Optional;

public class GetUserUseCaseImpl implements GetUserUseCase{
    private final UserGateway userGateway;

    public GetUserUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public Optional<User> execute(long userId) {
    if (userId <= 0) {
            throw new RuntimeException("Invalid user ID: " + userId);
        }
        return userGateway.findById(userId);
    }

    @Override
    public Optional<User> execute(String email) {
        return userGateway.findByEmail(email);
    }
}
