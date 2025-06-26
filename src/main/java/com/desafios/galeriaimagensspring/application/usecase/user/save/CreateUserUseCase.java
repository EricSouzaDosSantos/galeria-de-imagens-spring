package com.desafios.galeriaimagensspring.application.usecase.user.save;

import com.desafios.galeriaimagensspring.core.model.User;

public interface CreateUserUseCase {
    void execute(User user);
}
