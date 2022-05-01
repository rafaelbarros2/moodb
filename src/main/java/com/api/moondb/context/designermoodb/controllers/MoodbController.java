package com.api.moondb.context.designermoodb.controllers;

import com.api.moondb.context.designermoodb.model.Moodb;
import com.api.moondb.context.designermoodb.model.response.MoodbResponse;
import com.api.moondb.context.designermoodb.services.MoodbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/moodbs")
public class MoodbController {

    private final MoodbService moodbService;

    @PostMapping
    public ResponseEntity<MoodbResponse> save(@Valid @RequestBody Moodb moodb, @RequestHeader("token") String token) {
        MoodbResponse response = moodbService.insertMoodb(moodb, token);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{ownerMoodb}/{id}/enter")
    public ResponseEntity<MoodbResponse> find(@PathVariable String ownerMoodb, @PathVariable Long id,  @RequestParam String nameCustomer ){
        MoodbResponse response = moodbService.find(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping
    public ResponseEntity<MoodbResponse> findAll( @RequestHeader("token") String token){
        MoodbResponse response = moodbService.findAll(token);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
