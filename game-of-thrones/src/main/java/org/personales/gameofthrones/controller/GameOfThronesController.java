package org.personales.gameofthrones.controller;

import com.github.javafaker.Faker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gameofthrones/characters")
public class GameOfThronesController {

    private Faker faker = new Faker();

    private List<String> characters = new ArrayList<>();

    @PostConstruct
    public void init(){
        for (int i = 0; i < 20; i++) {
            characters.add(faker.gameOfThrones().character());
        }
    }

    @GetMapping
    public ResponseEntity<List<String>> getCharacters(){
        return ResponseEntity.ok(characters);
    }
}
