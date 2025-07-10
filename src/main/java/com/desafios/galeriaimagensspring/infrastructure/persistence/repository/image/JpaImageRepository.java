package com.desafios.galeriaimagensspring.infrastructure.persistence.repository.image;

import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.core.gateways.ImageGateway;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.ImagemEntity;
import com.desafios.galeriaimagensspring.interfaces.mapper.ImageMapper;

import java.util.List;
import java.util.Optional;
public class JpaImageRepository implements ImageGateway {

    private final SpringDataImageRepository springDataRepository;

    public JpaImageRepository(SpringDataImageRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Optional<Imagem> save(Imagem imagem) {
        ImagemEntity entity = ImageMapper.toEntity(imagem);
        return Optional.of(ImageMapper.toDomain(springDataRepository.save(entity)));
    }

    @Override
    public Optional<Imagem> findById(Long id) {
        return springDataRepository.findById(id).map(ImageMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        springDataRepository.deleteById(id);
    }

    @Override
    public Optional<Imagem> findByImageURL(String url) {
        return springDataRepository.findByImageURL(url).map(ImageMapper::toDomain);
    }

    @Override
    public List<Imagem> findAll() {
        return springDataRepository.findAll().stream().map(ImageMapper::toDomain).toList();
    }

    @Override
    public void updateImageURLByImageURL(String newUrl, String oldUrl) {
        springDataRepository.updateImageURLByImageURL(newUrl, oldUrl);
    }

    @Override
    public void deleteByImageURL(String url) {
        springDataRepository.deleteByImageURL(url);
    }
}