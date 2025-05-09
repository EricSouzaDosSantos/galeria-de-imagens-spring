package com.desafios.galeriaimagensspring.interfaces.controller;

import com.desafios.galeriaimagensspring.application.dto.albums.SaveAlbumDto;
import com.desafios.galeriaimagensspring.domain.model.Album;
import com.desafios.galeriaimagensspring.domain.service.AlbumService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/albums")
@RequiredArgsConstructor
@Tag(name = "Álbuns", description = "End")
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody SaveAlbumDto album) {
        Album createdAlbum = albumService.createAlbum(album);
        return ResponseEntity.status(201).body(createdAlbum);
    }

    @GetMapping
    public ResponseEntity<List<SaveAlbumDto>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        return albumService.getAlbumById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody SaveAlbumDto album) {
        Album updated = albumService.updateAlbum(id, album);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
