package com.desafios.galeriaimagensspring.core.gateways;

import com.desafios.galeriaimagensspring.core.model.Imagem;
import java.util.List;
import java.util.Optional;

public interface ImageGateway {
    Optional<Imagem> save(Imagem image);
    Optional<Imagem> findById(Long id);
    void deleteById(Long id);
    Optional<Imagem> findByImageURL(String url);
    void deleteByImageURL(String url);
    List<Imagem> findAll();
    void updateImageURLByImageURL(String newUrl, String oldUrl);
}
