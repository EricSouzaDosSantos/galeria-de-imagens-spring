package com.desafios.galeriaimagensspring.infrastructure.persistence.repository.image;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.ImagemEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SpringDataImageRepository extends JpaRepository<ImagemEntity, Long> {
    Optional<ImagemEntity> findByImageURL(String url);
    void deleteByImageURL(String url);
    @Modifying
    @Transactional
    @Query("UPDATE ImagemEntity i SET i.imageURL = :newUrl WHERE i.imageURL = :oldUrl")
    void updateImageURLByImageURL(@Param("newUrl") String newUrl, @Param("oldUrl") String oldUrl);
}
