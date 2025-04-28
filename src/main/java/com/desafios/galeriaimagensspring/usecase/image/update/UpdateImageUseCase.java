package com.desafios.galeriaimagensspring.usecase.image.update;

import org.springframework.web.multipart.MultipartFile;

public interface UpdateImageUseCase {
    String execute(String oldImageURL, MultipartFile multipartFile);
}
