package com.desafios.galeriaimagensspring.infrastructure.security.autentication;

import com.desafios.galeriaimagensspring.application.exception.user.UnauthorizedException;
import com.desafios.galeriaimagensspring.application.exception.user.UserNotFoundException;
import com.desafios.galeriaimagensspring.application.usecase.user.get.GetUserUseCase;
import com.desafios.galeriaimagensspring.core.gateways.AuthGateway;
import com.desafios.galeriaimagensspring.core.model.Albums;
import com.desafios.galeriaimagensspring.core.model.Imagem;
import com.desafios.galeriaimagensspring.core.model.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuthServiceImpl implements AuthGateway {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;
    private final GetUserUseCase getUserUseCase;

    public SpringSecurityAuthServiceImpl(AuthenticationManager authenticationManager,
                                         JwtTokenService jwtTokenService,
                                         PasswordEncoder passwordEncoder,
                                         GetUserUseCase getUserUseCase) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
        this.getUserUseCase = getUserUseCase;
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.isAuthenticated())) {
            throw new UnauthorizedException("unauthenticated user");
        }
        return getUserUseCase.execute((String) authentication.getPrincipal())
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    @Override
    public void validateUserAuthorization(Imagem imagem) {
        User userLogged = getAuthenticatedUser();
        if (imagem.user().id() != userLogged.id()) {
            throw new UnauthorizedException("user not authorized");
        }
    }

    @Override
    public void validateUserAuthorization(Albums albums) {
        User userLogged = getAuthenticatedUser();
        if (albums.user().id() != userLogged.id()) {
            throw new UnauthorizedException("user not authorized");
        }
    }

    @Override
    public String authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenService.generateToken(userDetails);
        return token;
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
