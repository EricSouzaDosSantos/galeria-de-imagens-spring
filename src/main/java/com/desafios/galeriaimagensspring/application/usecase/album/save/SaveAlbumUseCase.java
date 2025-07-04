package com.desafios.galeriaimagensspring.application.usecase.album.save;

import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.core.model.User;
import java.util.List;
import java.util.Optional;

public interface SaveAlbumUseCase {
    Optional<Albums> execute(String name,
                             List<Imagem> imagens,
                             User user);
}
