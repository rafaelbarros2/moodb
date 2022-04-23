package com.api.moondb.context.designermoodb.model.response;

import com.api.moondb.context.designermoodb.model.Imagem;
import com.api.moondb.context.designermoodb.model.Moodb;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImagemResponse {

    private LocalDateTime timestamp;
    private String shareableLink;
    private String error;
    private short statusCode;
    private List<Imagem> imagens;
}
