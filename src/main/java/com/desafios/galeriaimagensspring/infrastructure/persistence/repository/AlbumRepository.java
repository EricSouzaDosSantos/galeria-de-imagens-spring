package com.desafios.galeriaimagensspring.infrastructure.persistence.repository;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
}
