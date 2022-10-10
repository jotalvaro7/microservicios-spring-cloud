package org.personales.gameofthrones.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TranslationService {

    public Map<String, String> words = new HashMap<>();

    //Create logger
    private static final Logger logger = LoggerFactory.getLogger(TranslationService.class);

    @PostConstruct
    public void init(){
        words.put("Hello", "Hola");
        words.put("Goodbye", "Adios");
        words.put("Yes", "Si");
        words.put("No", "No");
        words.put("Please", "Por favor");
    }

    @Cacheable("translations")
    public Optional<String> translate(String message) {
        logger.info("Doing translation {} ", message);
        for (String word : words.keySet()) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {

            }
            if (word.equals(message)) {
                return Optional.of(words.get(message));
            }
        }

        return Optional.empty();
    }

    //Limpia el cache translations
    @CacheEvict("translations")
    public void clearCache(String message) {

    }
}
