package com.centime.microservice1.controller;

import com.centime.microservice1.model.Person;
import com.centime.microservice1.service.EndpointService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
public class EndPointResource {
    private static final Gson gson = new Gson();

    @Autowired
    private EndpointService endpointService;

    @GetMapping("/healthcheck")
    public Health getHealthCheck() throws JsonProcessingException {
        return endpointService.getHealthCheckInfo();
    }

    @PostMapping(value = "greetPerson", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity greetMessage(@Valid @RequestBody Person person) {
        String concatenatedResponse = endpointService.getConcatenatedResponse(person);
        return ResponseEntity.ok(gson.toJson(concatenatedResponse));
    }
}