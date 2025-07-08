package com.desafios.galeriaimagensspring.application.usecase.user.save;

import com.desafios.galeriaimagensspring.core.model.enums.UserRole;

public interface SaveUserUseCase {
    void execute(String email, String password, UserRole role);
}
