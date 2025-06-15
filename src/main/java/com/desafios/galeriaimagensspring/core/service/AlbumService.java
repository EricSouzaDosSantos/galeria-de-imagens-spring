package com.desafios.galeriaimagensspring.core.service;

import com.desafios.galeriaimagensspring.interfaces.dto.albums.SaveAlbumDto;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    SaveAlbumDto createAlbum(SaveAlbumDto album);
    List<SaveAlbumDto> getAllAlbums();
    Optional<SaveAlbumDto> getAlbumById(Long id);
    SaveAlbumDto updateAlbum(Long id, SaveAlbumDto updatedAlbum);
    void deleteAlbum(Long id);
}