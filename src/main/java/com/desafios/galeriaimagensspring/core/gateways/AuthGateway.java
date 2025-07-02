package com.desafios.galeriaimagensspring.core.gateways;

import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.core.model.User;

public interface AuthGateway {
    User getAuthenticatedUser();
    void validateUserAuthorization(Imagem imagem);
}
