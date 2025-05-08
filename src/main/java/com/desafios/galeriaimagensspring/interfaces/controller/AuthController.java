package com.desafios.galeriaimagensspring.interfaces.controller;

import com.desafios.galeriaimagensspring.application.service.UserService;
import com.desafios.galeriaimagensspring.infrastructure.security.dtos.LoginUserDTO;
import com.desafios.galeriaimagensspring.infrastructure.security.dtos.RecoveryJwtTokenDTO;
import com.desafios.galeriaimagensspring.infrastructure.security.dtos.RegisterUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDTO> login(@RequestBody LoginUserDTO loginUserDTO){
        RecoveryJwtTokenDTO token = userService.authenticateUser(loginUserDTO);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserDTO registerUserDTO){
        userService.registerUser(registerUserDTO);
        return ResponseEntity.status(201).body("Usuário criado com sucesso");
    }
}
