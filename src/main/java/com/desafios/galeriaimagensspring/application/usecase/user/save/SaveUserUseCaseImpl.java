package com.desafios.galeriaimagensspring.application.usecase.user.save;

import com.desafios.galeriaimagensspring.core.gateways.AuthGateway;
import com.desafios.galeriaimagensspring.core.model.User;
import com.desafios.galeriaimagensspring.core.gateways.UserGateway;
import com.desafios.galeriaimagensspring.core.model.enums.UserRole;

import java.util.ArrayList;

public class SaveUserUseCaseImpl implements SaveUserUseCase {

    private final UserGateway userGateway;
    private final AuthGateway authGateway;

    public SaveUserUseCaseImpl(UserGateway userGateway,
                               AuthGateway authGateway) {
        this.userGateway = userGateway;
        this.authGateway = authGateway;
    }

    @Override
    public void execute(String email, String password, UserRole role) {
        String encodePassword = authGateway.encodePassword(password);
        User user = new User(
                0,
                email,
                encodePassword,
                role,
                new ArrayList<>(),
                new ArrayList<>()
        );
        userGateway.save(user);
    }
}