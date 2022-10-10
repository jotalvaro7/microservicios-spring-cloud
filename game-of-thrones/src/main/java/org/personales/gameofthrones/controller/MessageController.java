package org.personales.gameofthrones.controller;

import org.personales.gameofthrones.services.TranslationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/translations")
public class MessageController {

    @Autowired
    private TranslationService service;
    //create Logger
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);


    //create responseEntity
    @GetMapping
    public ResponseEntity<String> getTranslation(@RequestParam("message") String message) {
        logger.info("Message received {} ", message);
        Optional<String> translation = service.translate(message);
        if (translation.isPresent()) {
            return ResponseEntity.ok(translation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public void clearCache(@RequestParam("message") String message) {
        logger.info("Cleaning cache for {} ", message);
        service.clearCache(message);
    }

}
