package com.desafios.galeriaimagensspring.interfaces.mapper;

import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.AlbumEntity;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.ImagemEntity;
import java.util.List;

public class ImageMapper {

    public static ImagemEntity toEntity(Imagem imagem) {
        List<AlbumEntity> albumList = imagem.albums()
                .stream()
                .map(AlbumMapper::toEntity)
                .toList();
        ImagemEntity entity = new ImagemEntity();
        entity.setId(imagem.id());
        entity.setName(imagem.name());
        entity.setDescription(imagem.description());
        entity.setImageURL(imagem.imageUrl());
        entity.setAlternateText(imagem.alternateText());
        entity.setCreatedAt(imagem.createdAt());
        entity.setAlbums(albumList);
        entity.setUser(UserMapper.toEntitySummary(imagem.user()));
        return entity;
    }

    public static Imagem toDomain(ImagemEntity entity) {
        List<Albums> albumList = entity.getAlbums()
                .stream()
                .map(AlbumMapper::toDomain)
                .toList();
        Imagem imagem = new Imagem(
                entity.getId(),
                entity.getDescription(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getAlternateText(),
                entity.getImageURL(),
                albumList,
                UserMapper.toDomainSummary(entity.getUser())
        );
        return imagem;
    }
}
