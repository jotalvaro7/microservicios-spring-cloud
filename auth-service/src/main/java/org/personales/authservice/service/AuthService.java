package org.personales.authservice.service;

import org.modelmapper.ModelMapper;
import org.personales.authservice.config.JwtProvider;
import org.personales.authservice.model.dto.TokenDto;
import org.personales.authservice.model.dto.UserDto;
import org.personales.authservice.model.entities.UserEntity;
import org.personales.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ModelMapper mapper;

    public UserDto save(UserDto user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        });

        UserEntity userEntity = userRepository.save(new UserEntity(user.getUsername(), passwordEncoder.encode(user.getPassword())));
        return mapper.map(userEntity, UserDto.class);
    }

    public TokenDto login(UserDto user) {

        UserEntity userEntity = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                });

        if (!passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else {
            return new TokenDto(jwtProvider.createToken(userEntity));
        }

    }

    public TokenDto validateToken(String token){
        jwtProvider.validatetoken(token);
        String usernameFromToken = jwtProvider.getUsernameFromToken(token);
        userRepository.findByUsername(usernameFromToken).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        });
        return new TokenDto(token);

    }

}
