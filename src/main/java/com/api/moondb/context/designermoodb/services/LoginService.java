package com.api.moondb.context.designermoodb.services;

import com.api.moondb.context.auth.User;
import com.api.moondb.context.auth.model.request.LoginRequest;
import com.api.moondb.context.auth.model.response.LoginResponse;
import com.api.moondb.singleton.Auth;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class LoginService {


    public LoginResponse login(LoginRequest user) {
            for (User u : Auth.getUsers()) {
                if (u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword())) {
                    Random random = new Random();
                    String token = Long.toHexString(random.nextLong());
                    u.setHash(token);
                    return  LoginResponse.builder()
                            .timestamp(LocalDateTime.now())
                            .token(token)
                            .statusCode((short) 200)
                            .build();
                }
            }
        return  LoginResponse.builder()
                .timestamp(LocalDateTime.now())
                .statusCode((short) 401)
                .error("Usuário ou senha incorretos")
                .build();
    }

}
