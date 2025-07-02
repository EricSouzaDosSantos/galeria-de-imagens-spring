package com.desafios.galeriaimagensspring.infrastructure.persistence.repository.image;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.ImagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataImageRepository extends JpaRepository<ImagemEntity, Long> {
    Optional<ImagemEntity> findByImageURL(String url);
    void deleteByImageURL(String url);
    void updateImageURLByImageURL(String newUrl, String oldUrl);
}
