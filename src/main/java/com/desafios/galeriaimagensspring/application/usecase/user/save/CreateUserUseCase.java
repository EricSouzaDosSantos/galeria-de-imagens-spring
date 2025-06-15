package com.desafios.galeriaimagensspring.application.usecase.user.save;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.User;

public interface CreateUserUseCase {
    void execute(User user);
}
