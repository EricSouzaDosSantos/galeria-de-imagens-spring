package com.desafios.galeriaimagensspring.application.usecase.auth.validation;

import com.desafios.galeriaimagensspring.core.model.Imagem;

public interface ValidateUserAuthorizationUseCase {
    void execute(Imagem imagem);
}
