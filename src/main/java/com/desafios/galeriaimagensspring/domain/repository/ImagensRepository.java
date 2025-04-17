package com.desafios.galeriaimagensspring.domain.repository;

import com.desafios.galeriaimagensspring.domain.model.Imagens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagensRepository extends JpaRepository<Imagens, Long> {
}
