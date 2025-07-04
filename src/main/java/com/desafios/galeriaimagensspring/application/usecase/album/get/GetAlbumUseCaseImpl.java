package com.desafios.galeriaimagensspring.application.usecase.album.get;

import com.desafios.galeriaimagensspring.core.gateways.AlbumGateway;
import com.desafios.galeriaimagensspring.core.model.Albums;

import java.util.List;
import java.util.Optional;

public class GetAlbumUseCaseImpl implements GetAlbumUseCase{
    private final AlbumGateway albumGateway;

    public GetAlbumUseCaseImpl(AlbumGateway albumGateway) {
        this.albumGateway = albumGateway;
    }


    @Override
    public Optional<Albums> execute(long id) {
        return albumGateway.findById(id);
    }

    @Override
    public List<Albums> execute() {
        return albumGateway.findAll();
    }
}
