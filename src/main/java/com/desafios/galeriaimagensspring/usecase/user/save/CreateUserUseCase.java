package com.desafios.galeriaimagensspring.usecase.user.save;

import com.desafios.galeriaimagensspring.domain.model.User;

public interface CreateUserUseCase {
    void execute(User user);
}
