package com.desafios.galeriaimagensspring.application.usecase.album.update;

import com.desafios.galeriaimagensspring.core.model.Imagem;

public interface UpdateAlbumUseCase {
    void execute(long id, String name, Imagem image);
}
