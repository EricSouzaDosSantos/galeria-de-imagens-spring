package com.desafios.galeriaimagensspring.application.usecase.image.delete;

import com.desafios.galeriaimagensspring.core.gateways.ImageGateway;
import com.desafios.galeriaimagensspring.core.gateways.StorageGateway;

public class DeleteImageUseCaseImpl implements DeleteImageUseCase {

    private final StorageGateway storageGateway;
    private final ImageGateway imageGateway;

    public DeleteImageUseCaseImpl(StorageGateway storageGateway, ImageGateway imageGateway) {
        this.storageGateway = storageGateway;
        this.imageGateway = imageGateway;
    }

    @Override
    public void execute(String imageURL) {
        storageGateway.delete(imageURL);
        imageGateway.deleteByImageURL(imageURL);
    }
}
