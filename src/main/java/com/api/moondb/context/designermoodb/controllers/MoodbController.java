package com.api.moondb.context.designermoodb.controllers;

import com.api.moondb.context.designermoodb.Moodb;
import com.api.moondb.context.designermoodb.services.MoodbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moodbs")
public class MoodbController {

    private final MoodbService moodbService;

    @PostMapping("/")
    public ResponseEntity<String> login(@Valid @RequestBody Moodb moodb, @RequestHeader("token") String token) {
        return ResponseEntity.ok(moodbService.insertMoodb(moodb, token));
    }
}
