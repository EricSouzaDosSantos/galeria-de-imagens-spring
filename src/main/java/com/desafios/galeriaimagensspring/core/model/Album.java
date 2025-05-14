package com.desafios.galeriaimagensspring.core.model;

import com.desafios.galeriaimagensspring.domain.model.Imagens;
import com.desafios.galeriaimagensspring.domain.model.User;
import jakarta.persistence.*;

import java.util.List;

public record Album(
        long id,
        String name,
        List<Imagens> imagens,
        User user
) {
}
