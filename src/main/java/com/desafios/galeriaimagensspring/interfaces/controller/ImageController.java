package com.desafios.galeriaimagensspring.interfaces.controller;

import com.desafios.galeriaimagensspring.application.dto.GetImageDto;
import com.desafios.galeriaimagensspring.application.dto.SaveImageDTO;
import com.desafios.galeriaimagensspring.domain.model.Album;
import com.desafios.galeriaimagensspring.domain.model.Imagens;
import com.desafios.galeriaimagensspring.domain.service.ImagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImagemService imagemService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Imagens> saveImage(
            @RequestPart("image") MultipartFile image,
            @RequestPart("alternateText") String alternateText,
            @RequestPart(value = "name", required = false) String name,
            @RequestPart(value = "description", required = false) String description,
            @RequestPart(value = "albums", required = false) List<Album> albums) {

        SaveImageDTO saveImageDTO = new SaveImageDTO(name, description, alternateText, image, albums);
        Imagens savedImage = imagemService.saveImage(saveImageDTO);
        return ResponseEntity.status(201).body(savedImage);
    }


    @GetMapping
    public ResponseEntity<List<GetImageDto>> getAllImages() {
        List<GetImageDto> images = imagemService.findAllImages();
        return ResponseEntity.ok(images);
    }

    @PutMapping("/{oldImageUrl}")
    public ResponseEntity<Imagens> updateImage(@PathVariable String oldImageUrl, @RequestPart MultipartFile image) {
        Imagens updatedImage = imagemService.updateImage(oldImageUrl, image);
        return ResponseEntity.ok(updatedImage);
    }

    @DeleteMapping("/{imageURL}")
    public ResponseEntity<Void> deleteImage(@PathVariable String imageURL) {
        imagemService.deleteImage(imageURL);
        return ResponseEntity.noContent().build();
    }
}
