package com.desafios.galeriaimagensspring.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ImagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(nullable = false)
    private String name;

    @Column(name = "creation_date", nullable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column(name = "alternate_text")
    private String alternateText;

    @Column(name = "url_image")
    private String imageURL;

    @ManyToMany
    @JoinTable(name = "album_images",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private List<AlbumEntity> album;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
