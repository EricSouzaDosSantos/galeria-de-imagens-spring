package com.desafios.galeriaimagensspring.application.service;

import com.desafios.galeriaimagensspring.application.dto.GetImageDto;
import com.desafios.galeriaimagensspring.application.dto.SaveImageDTO;
import com.desafios.galeriaimagensspring.domain.exception.image.ImageNotFoundException;
import com.desafios.galeriaimagensspring.domain.exception.user.UnauthorizedException;
import com.desafios.galeriaimagensspring.domain.exception.user.UserNotFoundException;
import com.desafios.galeriaimagensspring.domain.model.Imagens;
import com.desafios.galeriaimagensspring.domain.model.User;
import com.desafios.galeriaimagensspring.domain.repository.ImagensRepository;
import com.desafios.galeriaimagensspring.domain.repository.UserRepository;
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

    public Imagens saveImage(SaveImageDTO saveImageDTO) {
        String imageURL = saveImageUseCase.execute(saveImageDTO.image(), getAuthenticatedUser().getEmail());
        Imagens imagem = new Imagens();
        imagem.setName((saveImageDTO.name() == null || saveImageDTO.name().isEmpty()) ? saveImageDTO.image().getOriginalFilename() : saveImageDTO.name());
        imagem.setDescription(saveImageDTO.description());
        imagem.setImageURL(imageURL);
        imagem.setAlternateText(saveImageDTO.alternateText());
        imagem.setCreationDate(new Date());
//        imagem.setAlbum(saveImageDTO.albums());
        User userlogged = getAuthenticatedUser();
        imagem.setUser(userlogged);

        return imagensRepository.save(imagem);
    }

    public void deleteImage(String imageURL) {
        Imagens imagem = imagensRepository.findByImageURL(imageURL)
                .orElseThrow(() -> new ImageNotFoundException("Image not found"));

        validateUserAuthorization(imagem);

        deleteImageUseCase.execute(imageURL);
    }

    public Imagens updateImage(String oldImageUrl, MultipartFile file){
        String newImageUrl = updateImageUseCase.execute(oldImageUrl, file);
        Imagens imagem = imagensRepository.findByImageURL(oldImageUrl)
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
                    imagem.getCreationDate(),
                    imagem.getAlternateText(),
                    imagem.getImageURL());
            return getImageDto;
        }).toList();
    }

    public GetImageDto findImageById(Long id) {
        Imagens imagem = imagensRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("Image not found"));
        validateUserAuthorization(imagem);
        return new GetImageDto(imagem.getName(),
                imagem.getDescription(),
                imagem.getCreationDate(),
                imagem.getAlternateText(),
                imagem.getImageURL());
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.isAuthenticated())) {
            throw new UnauthorizedException("unauthenticated user");
        }
        User user = userRepository.findByEmail((String) authentication.getPrincipal()).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user;
    }

    private void validateUserAuthorization(Imagens imagem) {
        User userLogged = getAuthenticatedUser();
        if (imagem.getUser().getId() != userLogged.getId()) {
            throw new UnauthorizedException("User not authorized to access this image");
        }
    }


}
