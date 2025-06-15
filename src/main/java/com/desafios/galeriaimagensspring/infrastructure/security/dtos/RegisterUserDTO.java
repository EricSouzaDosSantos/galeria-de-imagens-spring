package com.desafios.galeriaimagensspring.infrastructure.security.dtos;

import com.desafios.galeriaimagensspring.core.model.enums.UserRole;

public record RegisterUserDTO(String email, String password, UserRole role) {
}
