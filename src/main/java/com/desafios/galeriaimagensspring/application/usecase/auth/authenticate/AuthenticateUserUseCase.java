package com.desafios.galeriaimagensspring.application.usecase.auth.authenticate;

public interface AuthenticateUserUseCase {
    String execute(String email, String password);
}
