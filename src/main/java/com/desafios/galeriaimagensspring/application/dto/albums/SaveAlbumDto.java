package com.desafios.galeriaimagensspring.application.dto.albums;

import com.desafios.galeriaimagensspring.domain.model.Imagens;

import java.util.List;

public record SaveAlbumDto(String name, List<Imagens> imagensList) {
}
