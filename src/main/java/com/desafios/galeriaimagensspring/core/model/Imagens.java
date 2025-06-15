package com.desafios.galeriaimagensspring.core.model;



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
        Users User
) {
}
