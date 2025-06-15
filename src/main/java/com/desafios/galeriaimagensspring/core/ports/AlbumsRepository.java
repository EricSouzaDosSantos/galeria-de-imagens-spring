package com.desafios.galeriaimagensspring.core.ports;

import com.desafios.galeriaimagensspring.core.model.Albums;

import java.util.List;
import java.util.Optional;

public interface AlbumsRepository {
    Albums save(Albums album);
    List<Albums> findAll();
    Optional<Albums> findById(Long id);
    void deleteById(Long id);
}
