package com.desafios.galeriaimagensspring.application.usecase.image.save;

import com.desafios.galeriaimagensspring.core.model.Imagem;

import java.util.Optional;

public interface SaveImageUseCase {
    Optional<Imagem> execute(Imagem imagem);
}
