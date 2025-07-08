package com.desafios.galeriaimagensspring.application.usecase.image.save;

import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.core.gateways.ImageGateway;

import java.util.Optional;

public class SaveImageUseCaseImpl implements SaveImageUseCase {

    private final ImageGateway imageGateway;

    public SaveImageUseCaseImpl(ImageGateway imageGateway) {
        this.imageGateway = imageGateway;
    }

    @Override
    public Optional<Imagem> execute(Imagem image) {
        return imageGateway.save(image);
    }
}
