package com.desafios.galeriaimagensspring.application.service;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.UserEntity;
import com.desafios.galeriaimagensspring.infrastructure.security.autentication.UserDetailsImpl;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.UserRepository;
import com.desafios.galeriaimagensspring.infrastructure.security.autentication.JwtTokenService;
import com.desafios.galeriaimagensspring.infrastructure.security.config.SecurityConfiguration;
import com.desafios.galeriaimagensspring.infrastructure.security.dtos.LoginUserDTO;
import com.desafios.galeriaimagensspring.infrastructure.security.dtos.RecoveryJwtTokenDTO;
import com.desafios.galeriaimagensspring.infrastructure.security.dtos.RegisterUserDTO;
import com.desafios.galeriaimagensspring.application.usecase.user.save.CreateUserFolderUseCase;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenService jwtTokenService;

    private final UserRepository userRepository;

    private final SecurityConfiguration securityConfiguration;

    private final CreateUserFolderUseCase createUserFolderUseCase;

    public RecoveryJwtTokenDTO authenticateUser(LoginUserDTO loginUserDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDTO.email(), loginUserDTO.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        System.out.println(authentication.getPrincipal());

        return new RecoveryJwtTokenDTO(jwtTokenService.generateToken(userDetails));
    }

    public void registerUser(RegisterUserDTO registerUserDTO){
        UserEntity newUser = UserEntity.builder()
                .email(registerUserDTO.email())
                .password(securityConfiguration.passwordEncoder().encode(registerUserDTO.password()))
                .userRole(registerUserDTO.role())
                .build();
        userRepository.save(newUser);
        createUserFolderUseCase.execute(registerUserDTO.email());
    }

}
