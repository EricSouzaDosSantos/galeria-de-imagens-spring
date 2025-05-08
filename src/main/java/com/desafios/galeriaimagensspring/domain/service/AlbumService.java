package com.desafios.galeriaimagensspring.domain.service;

import com.desafios.galeriaimagensspring.domain.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    Album createAlbum(Album album);
    List<Album> getAllAlbums();
    Optional<Album> getAlbumById(Long id);
    Album updateAlbum(Long id, Album updatedAlbum);
    void deleteAlbum(Long id);
}