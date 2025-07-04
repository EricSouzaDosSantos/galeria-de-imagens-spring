package com.desafios.galeriaimagensspring.application.usecase.album.save;

import com.desafios.galeriaimagensspring.core.gateways.AlbumGateway;
import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.core.model.User;

import java.util.List;
import java.util.Optional;

public class SaveAlbumUseCaseImpl implements SaveAlbumUseCase{
    private final AlbumGateway albumGateway;

    public SaveAlbumUseCaseImpl(AlbumGateway albumGateway) {
        this.albumGateway = albumGateway;
    }


    @Override
    public Optional<Albums> execute(String name, List<Imagem> imagens, User user) {
        Albums albums = new Albums(
                0,
                name,
                imagens,
                user
        );
        return albumGateway.save(albums);
    }
}
