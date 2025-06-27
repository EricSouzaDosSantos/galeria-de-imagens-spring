package com.desafios.galeriaimagensspring.core.model;

import java.util.List;

public record Albums(
        long id,
        String name,
        List<Imagem> imagens,
        User user
) {
}
