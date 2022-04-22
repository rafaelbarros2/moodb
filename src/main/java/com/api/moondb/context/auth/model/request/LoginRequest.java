package com.api.moondb.context.auth.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class LoginRequest {

      @NotBlank(message = "Senha Obrigatoria")
      private String login;
      @NotBlank(message = "Login Obrigatoria")
      private String password;

}
