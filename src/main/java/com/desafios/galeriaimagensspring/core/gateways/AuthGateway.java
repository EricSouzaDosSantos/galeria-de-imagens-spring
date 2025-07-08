package com.desafios.galeriaimagensspring.core.gateways;

import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.core.model.User;

public interface AuthGateway {
    User getAuthenticatedUser();
    void validateUserAuthorization(Imagem imagem);
    void validateUserAuthorization(Albums albums);
    String authenticate(String email, String password);
    String encodePassword(String password);
}
