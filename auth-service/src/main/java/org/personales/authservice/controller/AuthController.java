package org.personales.authservice.controller;

import org.personales.authservice.model.dto.TokenDto;
import org.personales.authservice.model.dto.UserDto;
import org.personales.authservice.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.login(userDto));
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token) {
        return ResponseEntity.ok(authService.validateToken(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        log.info("Registering user {}", userDto.getUsername());
        return ResponseEntity.ok(authService.save(userDto));
    }

}
