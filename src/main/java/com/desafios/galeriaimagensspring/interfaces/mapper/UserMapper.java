package com.desafios.galeriaimagensspring.interfaces.mapper;

import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.core.model.User;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.AlbumEntity;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.ImagemEntity;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ImageMapper.class, AlbumMapper.class})
public interface UserMapper {
    UserEntity toEntity(User user);
    User toDomain(UserEntity entity);

    @Named("toEntitySummary")
    @Mapping(target = "albums", ignore = true)
    @Mapping(target = "imagens", ignore = true)
    UserEntity toEntitySummary(User user);

    @Named("toDomainSummary")
    @Mapping(target = "albums", ignore = true)
    @Mapping(target = "imagens", ignore = true)
    User toDomainSummary(UserEntity entity);

//    public static UserEntity toEntity(user user) {
//        List<AlbumEntity> albumList = user.albums()
//                .stream()
//                .map(AlbumMapper::toEntity)
//                .toList();
//        List<ImagemEntity> imageList = user.imagens()
//                .stream()
//                .map(ImageMapper::toEntity)
//                .toList();
//        UserEntity entity = new UserEntity();
//        entity.setId(user.id());
//        entity.setEmail(user.email());
//        entity.setPassword(user.password());
//        entity.setUserRole(user.userRole());
//        entity.setAlbuns(albumList);
//        entity.setImagens(imageList);
//        return entity;
//    }
//
//    public static user toDomain(UserEntity entity) {
//        List<Albums> albumList = entity.getAlbuns()
//                .stream()
//                .map(AlbumMapper::toDomain)
//                .toList();
//        List<Imagem> imageList = entity.getImagens()
//                .stream()
//                .map(ImageMapper::toDomain)
//                .toList();
//        return new user(
//                entity.getId(),
//                entity.getEmail(),
//                entity.getPassword(),
//                entity.getUserRole(),
//                imageList,
//                albumList);
//    }
}