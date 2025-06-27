package com.desafios.galeriaimagensspring.core.ports;

import com.desafios.galeriaimagensspring.core.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
     User save(User user);
     List<User> findAll();
     Optional<User> findById(Long id);
     void deleteById(Long id);
}
