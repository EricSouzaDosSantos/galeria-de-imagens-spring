package com.desafios.galeriaimagensspring.infrastructure.persistence.repository.album;

import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.gateways.AlbumGateway;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.AlbumEntity;
import com.desafios.galeriaimagensspring.interfaces.mapper.AlbumMapper;
import com.desafios.galeriaimagensspring.interfaces.mapper.ImageMapper;

import java.util.List;
import java.util.Optional;

public class JpaAlbumRepository implements AlbumGateway {

    private final SpringDataAlbumRepository springDataRepository;
    private final AlbumMapper albumMapper;
    private final ImageMapper imageMapper;

    public JpaAlbumRepository(SpringDataAlbumRepository springDataRepository, AlbumMapper albumMapper, ImageMapper imageMapper) {
        this.springDataRepository = springDataRepository;
        this.albumMapper = albumMapper;
        this.imageMapper = imageMapper;
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

    @Override
    public Optional<Albums> update(long id, String name, Imagem imagem) {
    Optional<AlbumEntity> albumEntity = springDataRepository.findById(id);
    albumEntity.ifPresent(entity -> {
            entity.setName(name);
            entity.getImagens().add(imageMapper.toEntity(imagem));
            springDataRepository.save(entity);
        });
        return Optional.empty();
    }
}
