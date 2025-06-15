package com.desafios.galeriaimagensspring.application.service;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.UserEntity;
import com.desafios.galeriaimagensspring.interfaces.dto.imagens.GetImageDto;
import com.desafios.galeriaimagensspring.interfaces.dto.imagens.SaveImageDTO;
import com.desafios.galeriaimagensspring.application.exception.image.ImageNotFoundException;
import com.desafios.galeriaimagensspring.application.exception.user.UnauthorizedException;
import com.desafios.galeriaimagensspring.application.exception.user.UserNotFoundException;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.ImagemEntity;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.ImagensRepository;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.UserRepository;
import com.desafios.galeriaimagensspring.application.usecase.image.delete.DeleteImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.save.SaveImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.update.UpdateImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.user.save.CreateUserFolderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagemService {

    private final ImagensRepository imagensRepository;
    private final SaveImageUseCase saveImageUseCase;
    private final DeleteImageUseCase deleteImageUseCase;
    private final UpdateImageUseCase updateImageUseCase;
    private final UserRepository userRepository;
    private final CreateUserFolderUseCase createUserFolderUseCase;

    public ImagemEntity saveImage(SaveImageDTO saveImageDTO) {
        String imageURL = saveImageUseCase.execute(saveImageDTO.image(), getAuthenticatedUser().getEmail());
        ImagemEntity imagem = new ImagemEntity();
        imagem.setName((saveImageDTO.name() == null || saveImageDTO.name().isEmpty()) ? saveImageDTO.image().getOriginalFilename() : saveImageDTO.name());
        imagem.setDescription(saveImageDTO.description());
        imagem.setImageURL(imageURL);
        imagem.setAlternateText(saveImageDTO.alternateText());
        imagem.setCreatedAt(new Date());
//        imagem.setAlbum(saveImageDTO.albums());
        UserEntity userlogged = getAuthenticatedUser();
        imagem.setUser(userlogged);

        return imagensRepository.save(imagem);
    }

    public void deleteImage(String imageURL) {
        ImagemEntity imagem = imagensRepository.findByImageURL(imageURL)
                .orElseThrow(() -> new ImageNotFoundException("Image not found"));

        validateUserAuthorization(imagem);

        deleteImageUseCase.execute(imageURL);
    }

    public ImagemEntity updateImage(String oldImageUrl, MultipartFile file){
        String newImageUrl = updateImageUseCase.execute(oldImageUrl, file);
        ImagemEntity imagem = imagensRepository.findByImageURL(oldImageUrl)
                .orElseThrow(() -> new ImageNotFoundException("Image not found"));

        validateUserAuthorization(imagem);

        imagem.setImageURL(newImageUrl);
        imagem.setName(file.getOriginalFilename());
        return imagensRepository.save(imagem);
    }

    public List<GetImageDto> findAllImages() {
        return imagensRepository.findAll().stream().map(imagem -> {
            validateUserAuthorization(imagem);
            GetImageDto getImageDto = new GetImageDto(imagem.getName(),
                    imagem.getDescription(),
                    imagem.getCreatedAt(),
                    imagem.getAlternateText(),
                    imagem.getImageURL());
            return getImageDto;
        }).toList();
    }

    public GetImageDto findImageById(Long id) {
        ImagemEntity imagem = imagensRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("Image not found"));
        validateUserAuthorization(imagem);
        return new GetImageDto(imagem.getName(),
                imagem.getDescription(),
                imagem.getCreatedAt(),
                imagem.getAlternateText(),
                imagem.getImageURL());
    }

    public UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.isAuthenticated())) {
            throw new UnauthorizedException("unauthenticated user");
        }
        UserEntity user = userRepository.findByEmail((String) authentication.getPrincipal()).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user;
    }

    private void validateUserAuthorization(ImagemEntity imagem) {
        UserEntity userLogged = getAuthenticatedUser();
        if (imagem.getUser().getId() != userLogged.getId()) {
            throw new UnauthorizedException("User not authorized to access this image");
        }
    }


}
