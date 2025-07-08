package com.desafios.galeriaimagensspring.application.usecase.image.update;

import com.desafios.galeriaimagensspring.core.model.FileData;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateImageUseCase {
    String execute(String oldImageURL, FileData file);
    String execute(Imagem imagem);
}
