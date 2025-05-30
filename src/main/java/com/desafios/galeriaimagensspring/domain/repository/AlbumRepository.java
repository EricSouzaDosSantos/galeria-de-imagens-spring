package com.desafios.galeriaimagensspring.domain.repository;

import com.desafios.galeriaimagensspring.domain.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
