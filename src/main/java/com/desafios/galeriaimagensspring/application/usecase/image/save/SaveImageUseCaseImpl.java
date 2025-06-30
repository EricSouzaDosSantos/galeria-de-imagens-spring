package com.desafios.galeriaimagensspring.application.usecase.image.save;

import com.desafios.galeriaimagensspring.gateways.ImageGateway;
import org.springframework.web.multipart.MultipartFile;

public class SaveImageUseCaseImpl implements SaveImageUseCase {

    private final ImageGateway imageGateway;

    public SaveImageUseCaseImpl(ImageGateway imageGateway) {
        this.imageGateway = imageGateway;
    }

    @Override
    public String execute(MultipartFile image, String folderName) {
        return imageGateway.uploadImage(image, folderName);
    }
}
