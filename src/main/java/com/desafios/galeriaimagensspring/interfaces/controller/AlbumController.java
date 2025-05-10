package com.desafios.galeriaimagensspring.interfaces.controller;

import com.desafios.galeriaimagensspring.application.dto.albums.SaveAlbumDto;
import com.desafios.galeriaimagensspring.domain.model.Album;
import com.desafios.galeriaimagensspring.domain.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/albums")
@RequiredArgsConstructor
@Tag(name = "Albums", description = "Endpoints for managing image albums")
public class AlbumController {

    private final AlbumService albumService;

    @Operation(summary = "Create a new album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Album created successfully",
                    content = @Content(schema = @Schema(implementation = Album.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody SaveAlbumDto album) {
        Album createdAlbum = albumService.createAlbum(album);
        return ResponseEntity.status(201).body(createdAlbum);
    }

    @Operation(summary = "Get all albums")
    @ApiResponse(responseCode = "200", description = "List of all albums",
            content = @Content(schema = @Schema(implementation = SaveAlbumDto.class)))
    @GetMapping
    public ResponseEntity<List<SaveAlbumDto>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @Operation(summary = "Get an album by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Album found",
                    content = @Content(schema = @Schema(implementation = Album.class))),
            @ApiResponse(responseCode = "404", description = "Album not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        return albumService.getAlbumById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Album updated successfully",
                    content = @Content(schema = @Schema(implementation = Album.class))),
            @ApiResponse(responseCode = "404", description = "Album not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody SaveAlbumDto album) {
        Album updated = albumService.updateAlbum(id, album);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete an album by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Album deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Album not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
