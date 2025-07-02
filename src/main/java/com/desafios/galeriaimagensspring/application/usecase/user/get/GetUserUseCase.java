package com.desafios.galeriaimagensspring.application.usecase.user.get;

import com.desafios.galeriaimagensspring.core.model.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> execute(long userId);
    Optional<User> execute(String email);
}
