package org.personales.cloudstreamexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/messages")
@RestController
public class MessageController {

    @Autowired
    private StreamBridge streamBridge;


    @PostMapping
    public void sendMessage(@RequestBody String message) {
        streamBridge.send("toUpperCase-in-0", message);

    }

    }



