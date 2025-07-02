package com.desafios.galeriaimagensspring.infrastructure.persistence.repository.album;

import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.gateways.AlbumGateway;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.AlbumEntity;
import com.desafios.galeriaimagensspring.interfaces.mapper.AlbumMapper;

import java.util.List;
import java.util.Optional;

public class JpaAlbumRepository implements AlbumGateway {

    private final SpringDataAlbumRepository springDataRepository;
    private final AlbumMapper albumMapper;

    public JpaAlbumRepository(SpringDataAlbumRepository springDataRepository, AlbumMapper albumMapper) {
        this.springDataRepository = springDataRepository;
        this.albumMapper = albumMapper;
    }

    @Override
    public Optional<Albums> save(Albums album) {
        AlbumEntity albumEntity = albumMapper.toEntity(album);
        return Optional.of(albumMapper.toDomain(springDataRepository.save(albumEntity)));
    }

    @Override
    public List<Albums> findAll() {
        return springDataRepository.findAll()
                .stream()
                .map(albumMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Albums> findById(Long id) {
        return springDataRepository.findById(id)
                .map(albumMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        springDataRepository.deleteById(id);
    }
}
