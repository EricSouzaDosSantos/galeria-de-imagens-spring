package com.desafios.galeriaimagensspring.application.service;

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
    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public Album updateAlbum(Long id, Album updatedAlbum) {
        Album existingAlbum = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found"));

        existingAlbum.setName(updatedAlbum.getName());
        existingAlbum.setImagens(updatedAlbum.getImagens());
        return albumRepository.save(existingAlbum);
    }

    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
