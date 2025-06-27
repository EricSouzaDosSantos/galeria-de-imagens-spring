package com.desafios.galeriaimagensspring.gateways;

import com.desafios.galeriaimagensspring.core.model.Imagem;

public interface ImageGateway {
    Imagem save(Imagem image);
    Imagem findById(Long id);
    void deleteById(Long id);

}
