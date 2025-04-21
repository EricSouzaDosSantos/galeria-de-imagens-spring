package com.desafios.galeriaimagensspring.usecase.image.save;

import org.springframework.web.multipart.MultipartFile;

public interface SaveImageUseCase {
    String execute(MultipartFile image);
}
