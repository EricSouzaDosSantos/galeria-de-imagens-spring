package com.desafios.galeriaimagensspring.infrastructure.security.dtos;

import com.desafios.galeriaimagensspring.core.model.enums.UserRole;

public record RecoveryUserDTO(long id, String email, UserRole role) {
}
