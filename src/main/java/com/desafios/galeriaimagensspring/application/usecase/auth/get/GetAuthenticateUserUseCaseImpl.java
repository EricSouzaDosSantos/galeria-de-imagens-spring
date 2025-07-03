package com.desafios.galeriaimagensspring.application.usecase.auth.get;

import com.desafios.galeriaimagensspring.core.gateways.AuthGateway;
import com.desafios.galeriaimagensspring.core.model.User;

public class GetAuthenticateUserUseCaseImpl implements GetAuthenticateUserUseCase{
    private final AuthGateway authGateway;

    public GetAuthenticateUserUseCaseImpl(AuthGateway authGateway) {
        this.authGateway = authGateway;
    }

    @Override
    public User execute() {
        return authGateway.getAuthenticatedUser();
    }
}
