package com.desafios.galeriaimagensspring.domain.service;

import com.desafios.galeriaimagensspring.application.dto.albums.SaveAlbumDto;
import com.desafios.galeriaimagensspring.domain.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    SaveAlbumDto createAlbum(SaveAlbumDto album);
    List<SaveAlbumDto> getAllAlbums();
    Optional<SaveAlbumDto> getAlbumById(Long id);
    SaveAlbumDto updateAlbum(Long id, SaveAlbumDto updatedAlbum);
    void deleteAlbum(Long id);
}