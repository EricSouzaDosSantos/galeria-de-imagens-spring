package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.album;

import com.desafios.galeriaimagensspring.application.service.AlbumService;
import com.desafios.galeriaimagensspring.application.usecase.album.get.GetAlbumUseCase;
import com.desafios.galeriaimagensspring.application.usecase.album.save.SaveAlbumUseCase;
import com.desafios.galeriaimagensspring.application.usecase.album.update.UpdateAlbumUseCase;
import com.desafios.galeriaimagensspring.application.usecase.auth.get.GetAuthenticateUserUseCase;
import com.desafios.galeriaimagensspring.application.usecase.auth.validation.ValidateUserAuthorizationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlbumServiceConfig {

    @Bean
    public AlbumService albumService(
            SaveAlbumUseCase saveAlbumUseCase,
            GetAlbumUseCase getAlbumUseCase,
            UpdateAlbumUseCase updateAlbumUseCase,
            GetAuthenticateUserUseCase getAuthenticateUserUseCase,
            ValidateUserAuthorizationUseCase validateUserAuthorizationUseCase
    ){
        return new AlbumService(
                saveAlbumUseCase,
                getAlbumUseCase,
                updateAlbumUseCase,
                getAuthenticateUserUseCase,
                validateUserAuthorizationUseCase
        );
    }
}
