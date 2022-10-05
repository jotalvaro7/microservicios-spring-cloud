package org.personales.dragonball.controller;

import org.personales.dragonball.config.DragonBallConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application-name")
public class ApplicationNameController {

    private DragonBallConfig dragonBallConfig;

    private static final Logger Log = LoggerFactory.getLogger(ApplicationNameController.class);

    public ApplicationNameController(DragonBallConfig dragonBallConfig) {
        this.dragonBallConfig = dragonBallConfig;
    }



    @GetMapping
    public ResponseEntity<String> getAppName(){
        Log.info("Getting application name");
        return ResponseEntity.ok(dragonBallConfig.getApplicationName());
    }
}
