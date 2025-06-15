package com.desafios.galeriaimagensspring.interfaces.dto.albums;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.ImagemEntity;

import java.util.List;

public record SaveAlbumDto(String name, List<ImagemEntity> imagemEntityList) {
}
