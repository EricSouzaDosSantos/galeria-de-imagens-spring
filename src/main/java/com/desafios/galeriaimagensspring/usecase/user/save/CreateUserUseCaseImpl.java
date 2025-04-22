package com.desafios.galeriaimagensspring.usecase.user.save;

import com.desafios.galeriaimagensspring.domain.model.User;
import com.desafios.galeriaimagensspring.domain.repository.UserRepository;
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