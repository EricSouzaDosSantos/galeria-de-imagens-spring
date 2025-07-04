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
    public JpaAlbumRepository jpaAlbumRepository(SpringDataAlbumRepository springDataAlbumRepository,
                                                 AlbumMapper albumMapper,
                                                 ImageMapper imageMapper) {
        return new JpaAlbumRepository(springDataAlbumRepository,
                albumMapper,
                imageMapper);
    }

    @Bean
    public JpaImageRepository jpaImageRepository(SpringDataImageRepository springDataImageRepository,
                                                 ImageMapper imageMapper) {
        return new JpaImageRepository(springDataImageRepository,
                imageMapper);
    }

    @Bean
    public JpaUserRepository jpaUserRepository(SpringDataUserRepository springDataUserRepository,
                                               UserMapper userMapper) {
        return new JpaUserRepository(springDataUserRepository,
                userMapper);
    }
}
