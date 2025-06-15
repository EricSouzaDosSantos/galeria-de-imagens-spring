package com.desafios.galeriaimagensspring.core.ports;

import com.desafios.galeriaimagensspring.core.model.Users;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
     Users save(Users user);
     List<Users> findAll();
     Optional<Users> findById(Long id);
     void deleteById(Long id);
}
