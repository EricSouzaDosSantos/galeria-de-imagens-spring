package com.desafios.galeriaimagensspring.core.model;

import java.util.List;

public record Albums(
        long id,
        String name,
        List<Imagens> imagens,
        Users user
) {
}
