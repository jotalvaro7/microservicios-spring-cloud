package org.personales.authservice.controller;

import org.personales.authservice.model.dto.TokenDto;
import org.personales.authservice.model.dto.UserDto;
import org.personales.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authService.login(userDto));
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token){
        return ResponseEntity.ok(authService.validateToken(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authService.save(userDto));
    }

}
