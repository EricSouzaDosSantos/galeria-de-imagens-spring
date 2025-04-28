package com.desafios.galeriaimagensspring.domain.service;

import com.desafios.galeriaimagensspring.application.dto.GetImageDto;
import com.desafios.galeriaimagensspring.application.dto.SaveImageDTO;
import com.desafios.galeriaimagensspring.domain.model.Imagens;
import com.desafios.galeriaimagensspring.domain.repository.ImagensRepository;
import com.desafios.galeriaimagensspring.usecase.image.delete.DeleteImageUseCase;
import com.desafios.galeriaimagensspring.usecase.image.save.SaveImageUseCase;
import com.desafios.galeriaimagensspring.usecase.image.update.UpdateImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagemService {

    private final ImagensRepository imagensRepository;
    private final SaveImageUseCase saveImageUseCase;
    private final DeleteImageUseCase deleteImageUseCase;
    private final UpdateImageUseCase updateImageUseCase;

    public Imagens saveImage(SaveImageDTO saveImageDTO, MultipartFile file) {
        String imageURL = saveImageUseCase.execute(file);
        Imagens imagem = new Imagens();
        imagem.setName(file.getOriginalFilename());
        imagem.setDescription(saveImageDTO.description());
        imagem.setImageURL(imageURL);
        imagem.setAlternateText(saveImageDTO.alternateText());
        imagem.setAlbum(saveImageDTO.albums());

        return imagensRepository.save(imagem);
    }

    public void deleteImage(String imageURL) {
        deleteImageUseCase.execute(imageURL);
    }

    public Imagens updateImage(String oldImageUrl, MultipartFile file){
        String newImageUrl = updateImageUseCase.execute(oldImageUrl, file);
        Imagens imagem = imagensRepository.findByImageURL(oldImageUrl);
        imagem.setImageURL(newImageUrl);
        imagem.setName(file.getOriginalFilename());
        return imagensRepository.save(imagem);
    }

    public List<GetImageDto> findAllImages() {
        return imagensRepository.findAll().stream().map(imagem -> {
            GetImageDto getImageDto = new GetImageDto(imagem.getName(),
                    imagem.getDescription(),
                    imagem.getCreationDate(),
                    imagem.getAlternateText(),
                    imagem.getImageURL());
            return getImageDto;
        }).toList();
    }

    public GetImageDto findImageById(Long id) {
        Imagens imagem = imagensRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
        return new GetImageDto(imagem.getName(),
                imagem.getDescription(),
                imagem.getCreationDate(),
                imagem.getAlternateText(),
                imagem.getImageURL());
    }


}
