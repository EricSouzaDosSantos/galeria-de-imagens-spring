package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.user;

import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.user.JpaUserRepository;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.user.SpringDataUserRepository;
import com.desafios.galeriaimagensspring.interfaces.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRepositoryConfig {

    @Bean
    public JpaUserRepository jpaUserRepository(SpringDataUserRepository springDataUserRepository) {
        return new JpaUserRepository(springDataUserRepository);
    }
}
