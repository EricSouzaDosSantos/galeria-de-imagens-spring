package com.desafios.galeriaimagensspring.core.gateways;

import com.desafios.galeriaimagensspring.core.model.User;
import java.util.List;
import java.util.Optional;

public interface UserGateway {
    Optional<User> save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    void deleteById(Long id);
    List<User> findAll();
}
