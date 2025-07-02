package com.desafios.galeriaimagensspring.infrastructure.persistence.repository.album;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAlbumRepository extends JpaRepository<AlbumEntity, Long> {

}
