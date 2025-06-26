package com.desafios.galeriaimagensspring.gateways;

import com.desafios.galeriaimagensspring.core.model.User;

import java.util.List;

public interface UserGateway {
    User save(User user);
    User findById(Long id);
    User findByEmail(String email);
    void deleteById(Long id);
    void createUserFolder(String email);
    List<User> findAll();

}
