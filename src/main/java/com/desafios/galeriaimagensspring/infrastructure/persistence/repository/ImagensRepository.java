package com.desafios.galeriaimagensspring.infrastructure.persistence.repository;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.ImagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagensRepository extends JpaRepository<ImagemEntity, Long> {
    Optional<ImagemEntity> findByImageURL(String imageURL);
}
