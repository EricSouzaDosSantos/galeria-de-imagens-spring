package com.desafios.galeriaimagensspring.application.usecase.image.get;

import com.desafios.galeriaimagensspring.core.gateways.ImageGateway;
import com.desafios.galeriaimagensspring.core.model.Imagem;

import java.util.List;

public class GetImageUseCaseImpl implements GetImageUseCase{
    private final ImageGateway imageGateway;

    public GetImageUseCaseImpl(ImageGateway imageGateway) {
        this.imageGateway = imageGateway;
    }

    @Override
    public Imagem execute(long imageId) {
        return imageGateway.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found with ID: " + imageId));
    }

    @Override
    public Imagem execute(String imageURL) {
        return imageGateway.findByImageURL(imageURL)
                .orElseThrow(() -> new RuntimeException("Image not found with URL: " + imageURL));
    }

    @Override
    public List<Imagem> execute() {
        return imageGateway.findAll();
    }
}
