package com.desafios.galeriaimagensspring.application.usecase.user.save;

import com.desafios.galeriaimagensspring.core.service.StorageService;
import com.desafios.galeriaimagensspring.gateways.UserGateway;

public class CreateUserFolderUseCaseImpl implements CreateUserFolderUseCase {

    private final UserGateway userGateway;

    public CreateUserFolderUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void execute(String email) {
        userGateway.createUserFolder(email);
    }
}
