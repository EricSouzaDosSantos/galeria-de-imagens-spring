package com.desafios.galeriaimagensspring.application.service;

import com.desafios.galeriaimagensspring.application.usecase.album.get.GetAlbumUseCase;
import com.desafios.galeriaimagensspring.application.usecase.album.save.SaveAlbumUseCase;
import com.desafios.galeriaimagensspring.application.usecase.album.update.UpdateAlbumUseCase;
import com.desafios.galeriaimagensspring.application.usecase.auth.get.GetAuthenticateUserUseCase;
import com.desafios.galeriaimagensspring.application.usecase.auth.validation.ValidateUserAuthorizationUseCase;
import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.model.User;
import com.desafios.galeriaimagensspring.interfaces.dto.albums.AlbumRequestDTO;
import com.desafios.galeriaimagensspring.application.exception.album.AlbumNotFoundException;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.AlbumEntity;
import com.desafios.galeriaimagensspring.interfaces.dto.albums.AlbumResponseDTO;
import com.desafios.galeriaimagensspring.interfaces.dto.albums.UpdateAlbumRequestDTO;

import java.util.List;
import java.util.Optional;


public class AlbumService {

    private final SaveAlbumUseCase saveAlbumUseCase;
    private final GetAlbumUseCase getAlbumUseCase;
    private final UpdateAlbumUseCase updateAlbumUseCase;
    private final GetAuthenticateUserUseCase getAuthenticateUserUseCase;
    private final ValidateUserAuthorizationUseCase validateUserAuthorizationUseCase;

    public AlbumService(SaveAlbumUseCase saveAlbumUseCase,
                        GetAlbumUseCase getAlbumUseCase,
                        UpdateAlbumUseCase updateAlbumUseCase,
                        GetAuthenticateUserUseCase getAuthenticateUserUseCase,
                        ValidateUserAuthorizationUseCase validateUserAuthorizationUseCase) {
        this.saveAlbumUseCase = saveAlbumUseCase;
        this.getAlbumUseCase = getAlbumUseCase;
        this.updateAlbumUseCase = updateAlbumUseCase;
        this.getAuthenticateUserUseCase = getAuthenticateUserUseCase;
        this.validateUserAuthorizationUseCase = validateUserAuthorizationUseCase;
    }

    public AlbumResponseDTO createAlbum(AlbumRequestDTO albumDto) {
        User userlogged = getAuthenticateUserUseCase.execute();
        Optional<Albums> album = saveAlbumUseCase.execute(albumDto.name(), albumDto.imageList(), userlogged);
        if (album.isPresent()) {
            return builderAlbumResponseDTO(album.get());
        }
        throw new RuntimeException("Failed to create album");
    }

    public List<AlbumResponseDTO> getAllAlbums() {
        return getAlbumUseCase.execute()
                .stream()
                .map(this::builderAlbumResponseDTO)
                .toList();
    }

    public Optional<AlbumResponseDTO> getAlbumById(Long id) {
        return getAlbumUseCase.execute(id)
                .map(this::builderAlbumResponseDTO);
    }

    public void updateAlbum(Long id, UpdateAlbumRequestDTO updatedAlbum) {
       updateAlbumUseCase.execute(id, updatedAlbum.name(), updatedAlbum.imagem());
    }

//    public void deleteAlbum(Long id) {
//        jpaAlbumRepository.deleteById(id);
//    }

    public AlbumResponseDTO builderAlbumResponseDTO(Albums albums) {
        return new AlbumResponseDTO(
                albums.id(),
                albums.name(),
                albums.imagens());
    }
}
