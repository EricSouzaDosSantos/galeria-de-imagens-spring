package com.desafios.galeriaimagensspring.application.usecase.user.save;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.UserEntity;

public interface CreateUserUseCase {
    void execute(UserEntity user);
}
