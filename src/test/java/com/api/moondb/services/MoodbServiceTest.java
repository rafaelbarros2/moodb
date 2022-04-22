package com.api.moondb.services;

import com.api.moondb.context.auth.model.request.LoginRequest;
import com.api.moondb.context.designermoodb.Moodb;
import com.api.moondb.context.auth.User;
import com.api.moondb.context.designermoodb.services.LoginService;
import com.api.moondb.context.designermoodb.services.MoodbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MoodbServiceTest {

    private Moodb moodb;
    private LoginRequest user;

    @Autowired
    private LoginService loginService;

    @Autowired
    private MoodbService moodbService;

    @BeforeEach
    void setUp() {
        moodb =  new Moodb(2L, "rafael", "nao é",null);
        user = new LoginRequest( "rafael", "123");
    }

//    @Test
//    void insertMoodb() {
//        String link = "200 OK " +
//                "Shareable link: " + user.getLogin() + "/" + user.getId().toString() + "/" + "enter";
//        assertEquals(moodbService.insertMoodb(moodb,loginService.login(user)), link);
//
//    }

    @Test
    void SeHashForNullMoodb() {
        String link = "401 Unauthorized";
        assertFalse(moodbService.insertMoodb(moodb,"asdasfsadsa") == null);

    }

    @Test
    void SeHashForDiferente() {
        loginService.login(user);
        String link = "401 Unauthorized";
        assertEquals(moodbService.insertMoodb(moodb,"asdasfsadsa"), link);

    }
}