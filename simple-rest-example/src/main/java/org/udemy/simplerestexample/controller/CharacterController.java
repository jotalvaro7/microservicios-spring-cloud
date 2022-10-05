package org.udemy.simplerestexample.controller;


import com.github.javafaker.Faker;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.udemy.simplerestexample.error.CharacterNotFound;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Julio César
 */
//Recurso
@RequestMapping("/characters")
@RestController
public class CharacterController {

    private Faker faker = new Faker();
    private List<String> characters = new ArrayList<>();

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            characters.add(faker.dragonBall().character());
        }
    }

    @GetMapping("/dragonBall")
    public List<String> getCharacters() {
        return characters;
    }

    @GetMapping("/dragonBall/{name}")
    public String getCharactersByName(@PathVariable("name") String name) {
        return characters.stream()
                .filter(character -> character.equals(name))
                .findAny()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s not found", name)));
    }

    //search?param1=val1&param2=val2&param3=val3
    @GetMapping("/dragonBall/search")
    public List<String> getCharactersByPrefix(@RequestParam("prefix") String prefix) {
        List<String> result = characters.stream().filter(c -> c.startsWith(prefix)).collect(Collectors.toList());
        if(result.isEmpty()){
            throw new CharacterNotFound();
        }
        return result;
    }

}
