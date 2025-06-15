package com.desafios.galeriaimagensspring.application.usecase.user.save;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.User;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final CreateUserFolderUseCase createUserFolderUseCase;

    @Override
    public void execute(User user) {
        userRepository.save(user);
        createUserFolderUseCase.execute(user.getEmail());
    }
}