package com.desafios.galeriaimagensspring.interfaces.controller;

import com.desafios.galeriaimagensspring.application.service.UserService;
import com.desafios.galeriaimagensspring.infrastructure.security.dtos.LoginUserDTO;
import com.desafios.galeriaimagensspring.infrastructure.security.dtos.RecoveryJwtTokenDTO;
import com.desafios.galeriaimagensspring.infrastructure.security.dtos.RegisterUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Authenticate user and generate JWT token",
            description = "Authenticates a user with email and password, returning a JWT token to access protected resources.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "User credentials (email and password)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginUserDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Authentication successful, token returned"),
                    @ApiResponse(responseCode = "401", description = "Invalid credentials"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDTO> login(@RequestBody LoginUserDTO loginUserDTO){
        RecoveryJwtTokenDTO token = userService.authenticateUser(loginUserDTO);
        return ResponseEntity.ok().body(token);
    }

    @Operation(
            summary = "Register a new user",
            description = "Registers a new user with the provided details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "User registration details",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterUserDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "User successfully created"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserDTO registerUserDTO){
        userService.registerUser(registerUserDTO);
        return ResponseEntity.status(201).body("Usuário criado com sucesso");
    }
}
