package com.desafios.galeriaimagensspring.interfaces.dto.albums;

import com.desafios.galeriaimagensspring.core.model.Imagem;

import java.util.List;

public record AlbumRequestDTO(String name,
                              List<Imagem> imageList) {
}
