package com.api.moondb.services;

import com.api.moondb.context.auth.User;
import com.api.moondb.context.auth.model.request.LoginRequest;
import com.api.moondb.context.designermoodb.services.LoginService;
import com.api.moondb.singleton.Auth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

     private LoginRequest user;
     @Autowired
     private LoginService loginService;

    @BeforeEach
    void setUp() {
        user = new LoginRequest("rafael", "123");
    }


    @Test
    void login() {
        loginService.login(user);
        Auth.getUsers().stream()
                .filter(u -> u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword()))
                .findFirst()
                .ifPresent(
                        u -> assertFalse(u.getHash().isEmpty()));

    }

    @Test
    void errorlogin() {
        String msg = loginService.login(new LoginRequest("rafael", "1234")).getError();
        assertEquals("Usuário ou senha incorretos",msg);


    }
}