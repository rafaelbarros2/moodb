package com.api.moondb.context.designermoodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Moodb {

    private Long id;
    @NotBlank(message = "Nome do projeto Obrigatorio")
    private String name;
    private String subtitle;
    @NotNull
    private List<Imagem> imagens;
}
