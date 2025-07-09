package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.image;

import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.album.JpaAlbumRepository;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.album.SpringDataAlbumRepository;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.image.JpaImageRepository;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.image.SpringDataImageRepository;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.user.JpaUserRepository;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.user.SpringDataUserRepository;
import com.desafios.galeriaimagensspring.interfaces.mapper.AlbumMapper;
import com.desafios.galeriaimagensspring.interfaces.mapper.ImageMapper;
import com.desafios.galeriaimagensspring.interfaces.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageRepositoryConfig {

    @Bean
    public JpaImageRepository jpaImageRepository(SpringDataImageRepository springDataImageRepository,
                                                 ImageMapper imageMapper) {
        return new JpaImageRepository(springDataImageRepository,
                imageMapper);
    }
}
