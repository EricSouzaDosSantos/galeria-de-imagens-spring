package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.album;

import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.album.JpaAlbumRepository;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.album.SpringDataAlbumRepository;
import com.desafios.galeriaimagensspring.interfaces.mapper.AlbumMapper;
import com.desafios.galeriaimagensspring.interfaces.mapper.ImageMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlbumRepositoryConfig {
    @Bean
    public JpaAlbumRepository jpaAlbumRepository(
            SpringDataAlbumRepository springDataAlbumRepository) {
        return new JpaAlbumRepository(springDataAlbumRepository);
    }
}
