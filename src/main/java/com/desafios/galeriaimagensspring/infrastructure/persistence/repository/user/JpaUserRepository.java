package com.desafios.galeriaimagensspring.infrastructure.persistence.repository.user;

import com.desafios.galeriaimagensspring.core.model.User;
import com.desafios.galeriaimagensspring.core.gateways.UserGateway;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.UserEntity;
import com.desafios.galeriaimagensspring.interfaces.mapper.UserMapper;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class JpaUserRepository implements UserGateway {

    private final SpringDataUserRepository springDataRepository;

    public JpaUserRepository(SpringDataUserRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Optional<User> save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        return Optional.of(UserMapper.toDomain(springDataRepository.save(entity)));
    }

    @Override
    public Optional<User> findById(Long id) {
        return springDataRepository.findById(id)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataRepository.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        springDataRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return springDataRepository.findAll()
                .stream()
                .map(UserMapper::toDomain)
                .toList();
    }
}
