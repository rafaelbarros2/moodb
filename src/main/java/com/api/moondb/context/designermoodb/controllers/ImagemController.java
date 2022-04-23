package com.api.moondb.context.designermoodb.controllers;

import com.api.moondb.context.designermoodb.model.Imagem;
import com.api.moondb.context.designermoodb.model.Moodb;
import com.api.moondb.context.designermoodb.model.response.ImagemResponse;
import com.api.moondb.context.designermoodb.model.response.MoodbResponse;
import com.api.moondb.context.designermoodb.services.ImagemService;
import com.api.moondb.context.designermoodb.services.MoodbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/imagens")
public class ImagemController {

    private final ImagemService service;


    @PutMapping("/{id}")
    public ResponseEntity<ImagemResponse> find(@RequestBody List<Imagem> imagems, @PathVariable Long id ){
        ImagemResponse response = service.saveComments(id,imagems);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
