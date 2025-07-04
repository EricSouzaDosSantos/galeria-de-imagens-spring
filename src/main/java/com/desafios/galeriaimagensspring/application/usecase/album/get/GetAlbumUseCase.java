package com.desafios.galeriaimagensspring.application.usecase.album.get;

import com.desafios.galeriaimagensspring.core.model.Albums;

import java.util.List;
import java.util.Optional;

public interface GetAlbumUseCase {
    Optional<Albums> execute(long id);
    List<Albums> execute();
}
