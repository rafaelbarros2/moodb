package com.api.moondb.context.designermoodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Imagem {

    private Long id;
    @NotBlank(message = "Imagem sem referencia")
    private String reference;
    private String comment;
}
