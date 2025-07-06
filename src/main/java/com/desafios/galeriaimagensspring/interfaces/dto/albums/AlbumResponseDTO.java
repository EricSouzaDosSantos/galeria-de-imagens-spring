package com.desafios.galeriaimagensspring.interfaces.dto.albums;

import com.desafios.galeriaimagensspring.core.model.Imagem;

import java.util.List;

public record AlbumResponseDTO(
        long id,
        String name,
        List<Imagem> imagens
) {
}
