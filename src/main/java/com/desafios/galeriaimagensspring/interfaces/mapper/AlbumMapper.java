package com.desafios.galeriaimagensspring.interfaces.mapper;

import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.AlbumEntity;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.ImagemEntity;
import java.util.List;

public class AlbumMapper {

    public static AlbumEntity toEntity(Albums album) {
        AlbumEntity entity = new AlbumEntity();
        List<ImagemEntity> listImage = album.imagens().stream()
                .map(ImageMapper::toEntity)
                .toList();
        entity.setId(album.id());
        entity.setName(album.name());
        entity.setImagens(listImage);
        entity.setUser(UserMapper.toEntitySummary(album.user()));
        return entity;
    }

    public static Albums toDomain(AlbumEntity entity) {
        List<Imagem> imagens = entity.getImagens()
                .stream()
                .map(ImageMapper::toDomain)
                .toList();
        return new Albums(
                entity.getId(),
                entity.getName(),
                imagens,
                UserMapper.toDomainSummary(entity.getUser())
        );
    }
}
