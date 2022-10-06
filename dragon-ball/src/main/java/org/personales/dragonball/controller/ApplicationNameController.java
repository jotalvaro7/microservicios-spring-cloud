package org.personales.dragonball.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.personales.dragonball.config.DragonBallConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/application-name")
public class ApplicationNameController {

    private DragonBallConfig dragonBallConfig;

    @Autowired
    private MeterRegistry registry;

    private static final Logger Log = LoggerFactory.getLogger(ApplicationNameController.class);

    public ApplicationNameController(DragonBallConfig dragonBallConfig) {
        this.dragonBallConfig = dragonBallConfig;
    }



    @GetMapping
    public ResponseEntity<String> getAppName(){
        Log.info("Getting application name");
        int value = new Random().nextInt(5);
        registry.counter("dragonball.name", "level", (value<3)?"jr":"sr").increment(value);
        return ResponseEntity.ok(dragonBallConfig.getApplicationName());
    }
}
