package com.desafios.galeriaimagensspring.interfaces.dto.albums;

import com.desafios.galeriaimagensspring.core.model.Imagem;

public record UpdateAlbumRequestDTO(String name,
                                    Imagem imagem) {
}
