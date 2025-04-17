package com.desafios.galeriaimagensspring.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Data
public class Imagens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description_image")
    private String descriptionImage;

    @Column(nullable = false)
    private String nameImage;

    @Column(name = "creation_date", nullable = false)
    @CreatedDate
    private Date creationDate;

    @Column(name = "alternate_text")
    private String alternateText;

    @Column(name = "url_image")
    private String imageURL;
}
