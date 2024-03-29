package org.authexample.services;

import lombok.AllArgsConstructor;
import org.authexample.dtos.AuthRequest;
import org.authexample.dtos.RegisterRequest;
import org.authexample.entities.User;
import org.authexample.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtUtil jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Неправильный логин или пароль", HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }

    public ResponseEntity<?> createNewUser(RegisterRequest registerRequest) {
        if(userService.getUser(registerRequest.getUsername()) != null) {
            return new ResponseEntity<>("Такой пользователь уже существует", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setRoles(registerRequest.getRoles());
        userService.createUser(user);
        return new ResponseEntity<>("User created", HttpStatus.OK);
    }
}
