package com.desafios.galeriaimagensspring.gateways;

import com.desafios.galeriaimagensspring.core.model.Imagem;
import org.springframework.web.multipart.MultipartFile;

public interface ImageGateway {
    Imagem save(Imagem image);
    Imagem findById(Long id);
    void deleteById(Long id);
    String uploadImage(MultipartFile image, String folderName);

}
