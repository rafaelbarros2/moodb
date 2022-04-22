package com.api.moondb.context.auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    @NotBlank(message = "Senha Obrigatoria")
    private String password;
    @NotBlank(message = "Login Obrigatoria")
    private String login;
    private String hash;

}
