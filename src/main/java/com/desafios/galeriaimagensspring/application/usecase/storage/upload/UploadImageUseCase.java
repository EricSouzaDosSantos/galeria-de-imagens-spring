package com.desafios.galeriaimagensspring.application.usecase.storage.upload;

import com.desafios.galeriaimagensspring.core.model.FileData;

public interface UploadImageUseCase {
    String execute(FileData file, String folderName);
}
