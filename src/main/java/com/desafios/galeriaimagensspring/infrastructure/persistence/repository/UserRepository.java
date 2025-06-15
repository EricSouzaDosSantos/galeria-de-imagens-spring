package com.desafios.galeriaimagensspring.infrastructure.persistence.repository;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.User;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
