package com.desafios.galeriaimagensspring.core.model;

import com.desafios.galeriaimagensspring.core.model.enums.UserRole;

import java.util.List;

public record User(
        long id,
        String email,
        String password,
        UserRole userRole,
        List<Imagem> imagens,
        List<Imagem> albuns
) {
}
