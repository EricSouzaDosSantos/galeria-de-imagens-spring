package com.desafios.galeriaimagensspring.core.ports;

import com.desafios.galeriaimagensspring.core.model.Imagem;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    Imagem save(Imagem image);
    List<Imagem> findAll();
    Optional<Imagem> findById(Long id);
    void deleteById(Long id);
}
