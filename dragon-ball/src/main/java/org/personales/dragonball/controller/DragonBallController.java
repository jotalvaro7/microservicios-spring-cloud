package org.personales.dragonball.controller;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dragonball/characters")
public class DragonBallController {

    private Faker faker = new Faker();

    private static final Logger log = LoggerFactory.getLogger(DragonBallController.class);

    private List<String> characters = new ArrayList<>();

    @PostConstruct
    public void init(){
        for (int i = 0; i < 20; i++) {
            characters.add(faker.dragonBall().character());
        }
    }

    @GetMapping
    public ResponseEntity<List<String>> getCharacters(){
        log.info("Getting Characters db");
        return ResponseEntity.ok(characters);
    }
}
