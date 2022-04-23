package com.api.moondb.context.designermoodb.model.response;

import com.api.moondb.context.designermoodb.model.Moodb;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoodbResponse {

    private LocalDateTime timestamp;
    private String shareableLink;
    private String error;
    private short statusCode;
    private Moodb moodb;
}
