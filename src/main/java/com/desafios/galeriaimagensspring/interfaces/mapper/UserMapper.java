package com.desafios.galeriaimagensspring.interfaces.mapper;

import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.core.model.User;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.AlbumEntity;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.ImagemEntity;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.UserEntity;
import java.util.List;

public class UserMapper {

    public static UserEntity toEntitySummary(User user) {
        if (user == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(user.id());
        entity.setEmail(user.email());
        entity.setPassword(user.password());
        entity.setUserRole(user.userRole());
        return entity;
    }

    public static User toDomainSummary(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getUserRole(),
                null,
                null
        );
    }

    public static UserEntity toEntity(User user) {
        List<AlbumEntity> albumList = user.albums()
                .stream()
                .map(AlbumMapper::toEntity)
                .toList();
        List<ImagemEntity> imageList = user.imagens()
                .stream()
                .map(ImageMapper::toEntity)
                .toList();
        UserEntity entity = new UserEntity();
        entity.setId(user.id());
        entity.setEmail(user.email());
        entity.setPassword(user.password());
        entity.setUserRole(user.userRole());
        entity.setAlbums(albumList);
        entity.setImagens(imageList);
        return entity;
    }

    public static User toDomain(UserEntity entity) {
        List<Albums> albumList = entity.getAlbums()
                .stream()
                .map(AlbumMapper::toDomain)
                .toList();
        List<Imagem> imageList = entity.getImagens()
                .stream()
                .map(ImageMapper::toDomain)
                .toList();
        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getUserRole(),
                imageList,
                albumList);
    }
}