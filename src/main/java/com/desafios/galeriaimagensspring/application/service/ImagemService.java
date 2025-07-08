package com.desafios.galeriaimagensspring.application.service;

import com.desafios.galeriaimagensspring.application.usecase.auth.get.GetAuthenticateUserUseCase;
import com.desafios.galeriaimagensspring.application.usecase.auth.validation.ValidateUserAuthorizationUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.get.GetImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.storage.upload.UploadImageUseCase;
import com.desafios.galeriaimagensspring.core.model.FileData;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.core.model.User;
import com.desafios.galeriaimagensspring.interfaces.dto.file.FileMapper;
import com.desafios.galeriaimagensspring.interfaces.dto.imagens.ImageResponseDto;
import com.desafios.galeriaimagensspring.interfaces.dto.imagens.ImageRequestDTO;
import com.desafios.galeriaimagensspring.application.usecase.image.delete.DeleteImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.save.SaveImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.update.UpdateImageUseCase;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ImagemService {

    private final UploadImageUseCase uploadImageUseCase;
    private final SaveImageUseCase saveImageUseCase;
    private final GetImageUseCase getImageUseCase;
    private final DeleteImageUseCase deleteImageUseCase;
    private final UpdateImageUseCase updateImageUseCase;
    private final GetAuthenticateUserUseCase getAuthenticateUserUseCase;
    private final ValidateUserAuthorizationUseCase validateUserAuthorizationUseCase;

    public ImagemService(UploadImageUseCase uploadImageUseCase,
                         SaveImageUseCase saveImageUseCase,
                         GetImageUseCase getImageUseCase,
                         DeleteImageUseCase deleteImageUseCase,
                         UpdateImageUseCase updateImageUseCase,
                         GetAuthenticateUserUseCase getAuthenticateUserUseCase,
                         ValidateUserAuthorizationUseCase validateUserAuthorizationUseCase) {
        this.uploadImageUseCase = uploadImageUseCase;
        this.saveImageUseCase = saveImageUseCase;
        this.getImageUseCase = getImageUseCase;
        this.deleteImageUseCase = deleteImageUseCase;
        this.updateImageUseCase = updateImageUseCase;
        this.getAuthenticateUserUseCase = getAuthenticateUserUseCase;
        this.validateUserAuthorizationUseCase = validateUserAuthorizationUseCase;
    }


    public ImageResponseDto saveImage(ImageRequestDTO imageRequestDTO) {
        User userlogged = getAuthenticateUserUseCase.execute();
        FileData fileData = FileMapper.toFileData(imageRequestDTO.image());
        String imageURL = uploadImageUseCase.execute(fileData, userlogged.email());
        String imageName = (imageRequestDTO.name() == null || imageRequestDTO.name().isEmpty()) ?
                imageRequestDTO.image().getOriginalFilename() : imageRequestDTO.name();
        Imagem imagem = new Imagem(
                0,
                imageName,
                imageRequestDTO.description(),
                LocalDate.now(),
                imageRequestDTO.alternateText(),
                imageURL,
                new ArrayList<>(),
                userlogged
        );
        saveImageUseCase.execute(imagem)
                .orElseThrow(() -> new RuntimeException("Failed to save image"));

        return imageResponseDTOBuilder(imagem);
    }

    public void deleteImage(String imageURL) {
        Imagem imagem = getImageUseCase.execute(imageURL);
        validateUserAuthorizationUseCase.execute(imagem);
        deleteImageUseCase.execute(imageURL);
    }

    public ImageResponseDto updateImage(String oldImageUrl, FileData file) {
//        FileData fileData = fileDataBuilder(file);
        String newImageUrl = updateImageUseCase.execute(oldImageUrl, file);
        Imagem imagem = getImageUseCase.execute(newImageUrl);
        validateUserAuthorizationUseCase.execute(imagem);
        return imageResponseDTOBuilder(imagem);
    }

    public List<ImageResponseDto> findAllImages() {
        return getImageUseCase.execute().stream().map(imagem -> {
            validateUserAuthorizationUseCase.execute(imagem);
            return imageResponseDTOBuilder(imagem);
        }).toList();
    }

    public ImageResponseDto findImageById(Long id) {
        Imagem imagem = getImageUseCase.execute(id);
        validateUserAuthorizationUseCase.execute(imagem);
        return imageResponseDTOBuilder(imagem);
    }

    public ImageResponseDto imageResponseDTOBuilder(Imagem imagem) {
        return new ImageResponseDto(
                imagem.name(),
                imagem.description(),
                imagem.createdAt(),
                imagem.alternateText(),
                imagem.url()
        );
    }
}
