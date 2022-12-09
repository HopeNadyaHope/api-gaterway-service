package com.epam.mocroservices.microservices.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;

@Controller
@RequestMapping("/monitoring")
public class MonitoringController {
    @Value("${monitoring.uri}")
    private String monitoringUri;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<Void> monitoring() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(monitoringUri));
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
