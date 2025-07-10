package com.desafios.galeriaimagensspring.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "albums", cascade = CascadeType.ALL)
    private List<ImagemEntity> imagens;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}