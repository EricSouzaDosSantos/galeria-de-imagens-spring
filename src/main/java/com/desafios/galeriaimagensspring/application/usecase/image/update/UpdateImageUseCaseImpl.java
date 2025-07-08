package com.desafios.galeriaimagensspring.application.usecase.image.update;

import com.desafios.galeriaimagensspring.core.gateways.ImageGateway;
import com.desafios.galeriaimagensspring.core.gateways.StorageGateway;
import com.desafios.galeriaimagensspring.core.model.FileData;
import com.desafios.galeriaimagensspring.core.model.Imagem;

public class UpdateImageUseCaseImpl implements UpdateImageUseCase {

    private final StorageGateway storageGateway;
    private final ImageGateway imageGateway;

    public UpdateImageUseCaseImpl(StorageGateway storageGateway, ImageGateway imageGateway) {
        this.storageGateway = storageGateway;
        this.imageGateway = imageGateway;
    }

    @Override
    public String execute(String oldImageURL, FileData file) {
        String newUrl = storageGateway.updateImage(oldImageURL, file);
        imageGateway.updateImageURLByImageURL(oldImageURL, newUrl);
        return newUrl;
    }

    @Override
    public String execute(Imagem imagem) {
        return "";
    }
}
