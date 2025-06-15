package com.desafios.galeriaimagensspring.core.ports;

import com.desafios.galeriaimagensspring.core.model.Imagens;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    Imagens save(Imagens image);
    List<Imagens> findAll();
    Optional<Imagens> findById(Long id);
    void deleteById(Long id);
}
