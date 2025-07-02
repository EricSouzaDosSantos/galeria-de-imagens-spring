package com.desafios.galeriaimagensspring.interfaces.mapper;

import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.AlbumEntity;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.ImagemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, AlbumMapper.class})
public interface ImageMapper {

    @Mapping(target = "user", qualifiedByName = "toEntitySummary")
    ImagemEntity toEntity(Imagem imagem);

    @Mapping(target = "user", qualifiedByName = "toDomainSummary")
    Imagem toDomain(ImagemEntity entity);

    List<ImagemEntity> toEntityList(List<Imagem> imagens);

    List<Imagem> toDomainList(List<ImagemEntity> entities);

//    public static ImagemEntity toEntity(Imagem imagem) {
//        List<AlbumEntity> albumList = imagem.albuns()
//                .stream()
//                .map(AlbumMapper::toEntity)
//                .toList();
//        ImagemEntity entity = new ImagemEntity();
//        entity.setId(imagem.id());
//        entity.setName(imagem.name());
//        entity.setDescription(imagem.description());
//        entity.setImageURL(imagem.url());
//        entity.setAlternateText(imagem.alternateText());
//        entity.setCreatedAt(imagem.createdAt());
//        entity.setAlbum(albumList);
//        entity.setUser(UserMapper.toEntity(imagem.user()));
//        return entity;
//    }
//
//    public static Imagem toDomain(ImagemEntity entity) {
//        List<Albums> albumList = entity.getAlbum()
//                .stream()
//                .map(AlbumMapper::toDomain)
//                .toList();
//        Imagem imagem = new Imagem(
//                entity.getId(),
//                entity.getDescription(),
//                entity.getName(),
//                entity.getCreatedAt(),
//                entity.getAlternateText(),
//                entity.getImageURL(),
//                albumList,
//                UserMapper.toDomain(entity.getUser())
//        );
//        return imagem;
//    }
}
