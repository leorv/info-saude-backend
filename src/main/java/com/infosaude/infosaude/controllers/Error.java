package com.infosaude.infosaude.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "error2")
public class Error {
    @Value("${leonardo.ruoso.vendramini}")
    private String leonardo;

    @GetMapping
    public ResponseEntity<String> getAllEvents() {
        return ResponseEntity.ok(leonardo);
    }
}
