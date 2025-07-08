package com.desafios.galeriaimagensspring.interfaces.controller;

import com.desafios.galeriaimagensspring.interfaces.dto.file.FileMapper;
import com.desafios.galeriaimagensspring.interfaces.dto.imagens.ImageResponseDto;
import com.desafios.galeriaimagensspring.interfaces.dto.imagens.ImageRequestDTO;
import com.desafios.galeriaimagensspring.application.service.ImagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
@Tag(name = "Imagens", description = "Endpoint to manage images")
public class ImageController {

    private final ImagemService imagemService;

    public ImageController(ImagemService imagemService) {
        this.imagemService = imagemService;
    }

    @Operation(
            summary = "Upload an image",
            description = "Allows the authenticated user to upload an image with optional name and description.",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = "multipart/form-data",
                            schema = @Schema(implementation = ImageRequestDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Image successfully uploaded"),
                    @ApiResponse(responseCode = "401", description = "user not authenticated"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ImageResponseDto> saveImage(
            @RequestPart("image") MultipartFile image,
            @RequestPart("alternateText") String alternateText,
            @RequestPart(value = "name", required = false) String name,
            @RequestPart(value = "description", required = false) String description) {

        ImageRequestDTO imageRequestDTO = new ImageRequestDTO(name, description, alternateText, image);
        ImageResponseDto savedImage = imagemService.saveImage(imageRequestDTO);
        return ResponseEntity.status(201).body(savedImage);
    }


    @Operation(
            summary = "Get all images",
            description = "Allows the authenticated user to retrieves a list of all images.",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ImageResponseDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Image successfully uploaded"),
                    @ApiResponse(responseCode = "401", description = "user not authenticated"),
                    @ApiResponse(responseCode = "403", description = "user not authorized to update this image"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping
    public ResponseEntity<List<ImageResponseDto>> getAllImages() {
        List<ImageResponseDto> images = imagemService.findAllImages();
        return ResponseEntity.ok(images);
    }

    @Operation(
            summary = "Update an image",
            description = "Allows the only authenticated and authorized user to update an image.",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = "multipart/form-data",
                            schema = @Schema(implementation = ImageResponseDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Image successfully updated"),
                    @ApiResponse(responseCode = "401", description = "user not authenticated"),
                    @ApiResponse(responseCode = "403", description = "user not authorized to update this image"),
                    @ApiResponse(responseCode = "404", description = "Image not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping("/{oldImageUrl}")
    public ResponseEntity<ImageResponseDto> updateImage(@PathVariable String oldImageUrl, @RequestPart MultipartFile image) {
        ImageResponseDto updatedImage = imagemService.updateImage(oldImageUrl, FileMapper.toFileData(image));
        return ResponseEntity.ok(updatedImage);
    }

    @Operation(
            summary = "Delete an image",
            description = "Allows the authenticated user to delete an image.",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Void.class)

                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Image successfully deleted"),
                    @ApiResponse(responseCode = "401", description = "user not authenticated"),
                    @ApiResponse(responseCode = "403", description = "user not authorized to delete this image"),
                    @ApiResponse(responseCode = "404", description = "Image not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/{imageURL}")
    public ResponseEntity<Void> deleteImage(@PathVariable String imageURL) {
        imagemService.deleteImage(imageURL);
        return ResponseEntity.noContent().build();
    }
}
