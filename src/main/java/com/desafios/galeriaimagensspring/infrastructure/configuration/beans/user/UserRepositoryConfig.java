package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.user;

import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.user.JpaUserRepository;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.user.SpringDataUserRepository;
import com.desafios.galeriaimagensspring.interfaces.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryConfig {

    @Bean
    public JpaUserRepository jpaUserRepository(SpringDataUserRepository springDataUserRepository,
                                               UserMapper userMapper) {
        return new JpaUserRepository(springDataUserRepository, userMapper);
    }
}
