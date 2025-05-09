package com.desafios.galeriaimagensspring.application.service;

import com.desafios.galeriaimagensspring.application.dto.albums.SaveAlbumDto;
import com.desafios.galeriaimagensspring.domain.model.Album;
import com.desafios.galeriaimagensspring.domain.repository.AlbumRepository;
import com.desafios.galeriaimagensspring.domain.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    @Override
    public Album createAlbum(SaveAlbumDto albumDto) {
        Album album = new Album();
        album.setName(albumDto.name());
        album.setImagens(albumDto.imagensList());
        return albumRepository.save(album);
    }

    @Override
    public List<SaveAlbumDto> getAllAlbums() {
        return albumRepository.findAll().stream().map(album -> new SaveAlbumDto(album.getName(), album.getImagens())).toList();
    }

    @Override
    public Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public Album updateAlbum(Long id, SaveAlbumDto updatedAlbum) {
        Album existingAlbum = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found"));

        existingAlbum.setName(updatedAlbum.name());
        existingAlbum.setImagens(updatedAlbum.imagensList());
        return albumRepository.save(existingAlbum);
    }

    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
