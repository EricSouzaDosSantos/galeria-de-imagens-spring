package com.desafios.galeriaimagensspring.application.service;

import com.desafios.galeriaimagensspring.application.usecase.auth.authenticate.AuthenticateUserUseCase;
import com.desafios.galeriaimagensspring.application.usecase.user.save.SaveUserUseCase;
import com.desafios.galeriaimagensspring.infrastructure.security.dtos.LoginUserDTO;
import com.desafios.galeriaimagensspring.infrastructure.security.dtos.RecoveryJwtTokenDTO;
import com.desafios.galeriaimagensspring.infrastructure.security.dtos.RegisterUserDTO;

public class UserService {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final SaveUserUseCase saveUserUseCase;

    public UserService(AuthenticateUserUseCase authenticateUserUseCase,
                       SaveUserUseCase saveUserUseCase) {
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.saveUserUseCase = saveUserUseCase;
    }

    public RecoveryJwtTokenDTO authenticateUser(LoginUserDTO loginUserDTO){
        String authenticatedToken = authenticateUserUseCase.execute(loginUserDTO.email(), loginUserDTO.password());
        return new RecoveryJwtTokenDTO(authenticatedToken);
    }

    public void registerUser(RegisterUserDTO registerUserDTO){
        saveUserUseCase.execute(registerUserDTO.email(), registerUserDTO.password(), registerUserDTO.role());
    }
}
