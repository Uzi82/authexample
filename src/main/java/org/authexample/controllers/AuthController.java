package org.authexample.controllers;

import lombok.AllArgsConstructor;
import org.authexample.dtos.AuthRequest;
import org.authexample.dtos.RegisterRequest;
import org.authexample.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AuthRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }
    @PostMapping("/reg")
    public ResponseEntity<?> reg(@RequestBody RegisterRequest registerRequest) {
        return authService.createNewUser(registerRequest);
    }
}
