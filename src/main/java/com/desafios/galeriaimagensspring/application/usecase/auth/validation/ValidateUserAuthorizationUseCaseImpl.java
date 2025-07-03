package com.desafios.galeriaimagensspring.application.usecase.auth.validation;

import com.desafios.galeriaimagensspring.core.gateways.AuthGateway;
import com.desafios.galeriaimagensspring.core.model.Imagem;

public class ValidateUserAuthorizationUseCaseImpl implements ValidateUserAuthorizationUseCase{
    private final AuthGateway authGateway;
    public ValidateUserAuthorizationUseCaseImpl(AuthGateway authGateway) {
        this.authGateway = authGateway;
    }
    @Override
    public void execute(Imagem imagem) {
        authGateway.validateUserAuthorization(imagem);
    }
}
