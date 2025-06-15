package com.desafios.galeriaimagensspring.application.service;

import com.desafios.galeriaimagensspring.interfaces.dto.albums.SaveAlbumDto;
import com.desafios.galeriaimagensspring.application.exception.album.AlbumNotFoundException;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.AlbumRepository;
import com.desafios.galeriaimagensspring.core.service.AlbumService;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.AlbumEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    @Override
    public SaveAlbumDto createAlbum(SaveAlbumDto albumDto) {
        AlbumEntity album = new AlbumEntity();
        album.setName(albumDto.name());
        album.setImagens(albumDto.imagemEntityList());
        albumRepository.save(album);
        return albumDto;
    }

    @Override
    public List<SaveAlbumDto> getAllAlbums() {
        return albumRepository.findAll().stream().map(album -> new SaveAlbumDto(album.getName(), album.getImagens())).toList();
    }

    @Override
    public Optional<SaveAlbumDto> getAlbumById(Long id) {
        return Optional.ofNullable(albumRepository.findById(id).map(album ->
                        new SaveAlbumDto(album.getName(),
                                album.getImagens()))
                .orElseThrow(() -> new AlbumNotFoundException("Album not found")));
    }

    @Override
    public SaveAlbumDto updateAlbum(Long id, SaveAlbumDto updatedAlbum) {
        AlbumEntity existingAlbum = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found"));

        existingAlbum.setName(updatedAlbum.name());
        existingAlbum.setImagens(updatedAlbum.imagemEntityList());
        albumRepository.save(existingAlbum);
        return updatedAlbum;
    }

    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
