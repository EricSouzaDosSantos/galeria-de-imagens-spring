package com.desafios.galeriaimagensspring.application.usecase.user.save;

import com.desafios.galeriaimagensspring.core.gateways.StorageGateway;

public class CreateUserFolderUseCaseImpl implements CreateUserFolderUseCase {

    private final StorageGateway storageGateway;

    public CreateUserFolderUseCaseImpl(StorageGateway storageGateway) {
        this.storageGateway = storageGateway;
    }

    @Override
    public void execute(String email) {
        storageGateway.createUserFolder(email);
    }
}
