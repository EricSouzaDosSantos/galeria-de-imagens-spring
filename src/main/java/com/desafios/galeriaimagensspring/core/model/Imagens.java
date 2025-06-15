package com.desafios.galeriaimagensspring.core.model;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.User;

import java.time.LocalDate;
import java.util.List;

public record Imagens(
        long id,
        String description,
        String name,
        LocalDate createdAt,
        String alternateText,
        String url,
        List<Albums> albuns,
        User User
) {
}
