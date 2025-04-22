package com.desafios.galeriaimagensspring.usecase.user.save;

import com.desafios.galeriaimagensspring.domain.repository.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserFolderUseCaseImpl implements CreateUserFolderUseCase {

    private final StorageService storageService;

    @Override
    public void execute(String email) {
        storageService.createUserFolder(email);
    }
}
