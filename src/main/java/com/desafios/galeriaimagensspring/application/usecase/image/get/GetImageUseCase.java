package com.desafios.galeriaimagensspring.application.usecase.image.get;

import com.desafios.galeriaimagensspring.core.model.Imagem;

import java.util.List;

public interface GetImageUseCase {
    Imagem execute(long imageId);
    Imagem execute(String imageURL);
    List<Imagem> execute();
}
