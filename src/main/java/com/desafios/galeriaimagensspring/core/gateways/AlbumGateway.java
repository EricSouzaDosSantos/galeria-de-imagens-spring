package com.desafios.galeriaimagensspring.core.gateways;

import com.desafios.galeriaimagensspring.core.model.Albums;

import java.util.List;
import java.util.Optional;

public interface AlbumGateway {
    Optional<Albums> save(Albums album);
    List<Albums> findAll();
    Optional<Albums> findById(Long id);
    void deleteById(Long id);
}
