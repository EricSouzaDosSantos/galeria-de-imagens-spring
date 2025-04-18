package com.desafios.galeriaimagensspring.application.service;

import com.desafios.galeriaimagensspring.domain.model.Album;
import com.desafios.galeriaimagensspring.domain.model.Imagens;
import com.desafios.galeriaimagensspring.domain.repository.AlbumRepository;
import com.desafios.galeriaimagensspring.domain.repository.ImagensRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ImagemService {

    private final ImagensRepository imagensRepository;
    private final AlbumRepository albumRepository;

    public Imagens execute(MultipartFile file, Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));

        Imagens imagem = new Imagens();
        imagem.setNameImage(file.getOriginalFilename());
        imagem.setImageURL("");
        imagem.setAlbum(album);

        return imagensRepository.save(imagem);
    }


}
