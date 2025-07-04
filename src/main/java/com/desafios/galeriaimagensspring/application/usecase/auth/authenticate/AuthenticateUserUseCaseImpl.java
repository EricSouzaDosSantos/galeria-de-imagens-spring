package com.desafios.galeriaimagensspring.application.usecase.auth.authenticate;

import com.desafios.galeriaimagensspring.core.gateways.AuthGateway;

public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {
    private final AuthGateway authGateway;

    public AuthenticateUserUseCaseImpl(AuthGateway authGateway) {
        this.authGateway = authGateway;
    }

    @Override
    public String execute(String email, String password) {
        return authGateway.authenticate(email, password);
    }
}
