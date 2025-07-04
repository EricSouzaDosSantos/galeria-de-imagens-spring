package com.desafios.galeriaimagensspring.application.usecase.album.update;

import com.desafios.galeriaimagensspring.application.usecase.album.get.GetAlbumUseCase;
import com.desafios.galeriaimagensspring.core.gateways.AlbumGateway;
import com.desafios.galeriaimagensspring.core.model.Imagem;

public class UpdateAlbumUseCaseImpl implements UpdateAlbumUseCase{
    private final AlbumGateway albumGateway;
    private final GetAlbumUseCase getAlbumUseCase;

    public UpdateAlbumUseCaseImpl(AlbumGateway albumGateway,
                                  GetAlbumUseCase getAlbumUseCase) {
        this.albumGateway = albumGateway;
        this.getAlbumUseCase = getAlbumUseCase;
    }

    @Override
    public void execute(long id, String name, Imagem image) {
        albumGateway.update(id, name, image);
    }
}
