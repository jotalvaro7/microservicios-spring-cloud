package org.personales.dragonball.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@Service
public class FooService {
    private static final Logger log = LoggerFactory.getLogger(FooService.class);
    @Autowired
    private Tracer tracer;

    public void printLog() {

        Span newSpan = tracer.nextSpan().name("newSpan");
        try (Tracer.SpanInScope ws = tracer.withSpan(newSpan.start())) {
            log.info("Test Log");
        } finally {
            newSpan.end();
        }

        Span newSpan2 = tracer.nextSpan().name("newSpan2");
        try (Tracer.SpanInScope ws = tracer.withSpan(newSpan2.start())) {
            log.info("Test Log newSpan2");
        } finally {
            newSpan2.end();
        }
        log.info("Fin");
    }

}
