package org.personales.gameofthrones.controller2;

import com.github.javafaker.Faker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/rest/gameofthrones/personajes")
public class GameOfThronesController2 {

    private Faker faker = new Faker();
    private List<String> characters = new ArrayList<>();

    private static final Logger log = LoggerFactory.getLogger(GameOfThronesController2.class);

    @PostConstruct
    public void init(){
        for (int i = 0; i < 20; i++) {
            characters.add(faker.gameOfThrones().character());
        }
    }

    //create anotation for swagger
    @Operation(summary = "Get all characters from Game of Thrones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the characters",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))),
            @ApiResponse(responseCode = "404", description = "Characters not found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<String>> getCharacters(){
        log.info("Getting Characters GoT");
        return ResponseEntity.ok(characters);
    }
}
