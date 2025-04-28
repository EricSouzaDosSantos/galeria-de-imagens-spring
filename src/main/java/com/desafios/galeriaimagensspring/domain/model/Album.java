package com.desafios.galeriaimagensspring.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Imagens> imagens;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}