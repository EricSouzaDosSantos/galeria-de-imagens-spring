package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.album;

import com.desafios.galeriaimagensspring.application.usecase.album.get.GetAlbumUseCase;
import com.desafios.galeriaimagensspring.application.usecase.album.get.GetAlbumUseCaseImpl;
import com.desafios.galeriaimagensspring.application.usecase.album.save.SaveAlbumUseCase;
import com.desafios.galeriaimagensspring.application.usecase.album.save.SaveAlbumUseCaseImpl;
import com.desafios.galeriaimagensspring.application.usecase.album.update.UpdateAlbumUseCase;
import com.desafios.galeriaimagensspring.application.usecase.album.update.UpdateAlbumUseCaseImpl;
import com.desafios.galeriaimagensspring.core.gateways.AlbumGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlbumUseCaseConfig {

    @Bean
    public SaveAlbumUseCase saveAlbumUseCase(AlbumGateway albumGateway){
        return new SaveAlbumUseCaseImpl(albumGateway);
    }

    @Bean
    public GetAlbumUseCase getAlbumUseCase(AlbumGateway albumGateway) {
        return new GetAlbumUseCaseImpl(albumGateway);
    }

    @Bean
    public UpdateAlbumUseCase updateAlbumUseCase(AlbumGateway albumGateway, GetAlbumUseCase getAlbumUseCase) {
        return new UpdateAlbumUseCaseImpl(albumGateway, getAlbumUseCase);
    }
}
