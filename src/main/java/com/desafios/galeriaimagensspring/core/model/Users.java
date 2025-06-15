package com.desafios.galeriaimagensspring.core.model;

import com.desafios.galeriaimagensspring.core.model.enums.UserRole;

import java.util.List;

public record Users(
        long id,
        String email,
        String password,
        UserRole userRole,
        List<Imagens> imagens,
        List<Imagens> albuns
) {
}
