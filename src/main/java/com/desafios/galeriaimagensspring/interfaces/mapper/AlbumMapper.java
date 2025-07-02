package com.desafios.galeriaimagensspring.interfaces.mapper;

import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.AlbumEntity;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.ImagemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

//    @Mapping(source = "name", target = "nomeDoAlbum")
    AlbumEntity toEntity(Albums album);

//    @Mapping(source = "nomeDoAlbum", target = "name")
    Albums toDomain(AlbumEntity entity);

//    how mapStruct works “behind the scenes”
//    public static AlbumEntity toEntity(Albums album) {
//        AlbumEntity entity = new AlbumEntity();
//        List<ImagemEntity> listImage = album.imagens().stream()
//                .map(ImageMapper::toEntity)
//                .toList();
//        entity.setId(album.id());
//        entity.setName(album.name());
//        entity.setImagens(listImage);
//        entity.setUser(UserMapper.toEntity(album.user()));
//        return entity;
//    }
//
//    public static Albums toDomain(AlbumEntity entity) {
//        List<Imagem> imagens = entity.getImagens()
//                .stream()
//                .map(ImageMapper::toDomain)
//                .toList();
//        return new Albums(
//                entity.getId(),
//                entity.getName(),
//                imagens,
//                UserMapper.toDomain(entity.getUser())
//        );
//    }
}
