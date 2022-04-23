package com.api.moondb.services;

import com.api.moondb.context.auth.model.User;
import com.api.moondb.context.auth.model.request.LoginRequest;
import com.api.moondb.context.auth.model.response.LoginResponse;
import com.api.moondb.context.designermoodb.model.Moodb;
import com.api.moondb.context.designermoodb.services.LoginService;
import com.api.moondb.context.designermoodb.services.MoodbService;
import com.api.moondb.singleton.Auth;
import com.api.moondb.singleton.MoodbList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class MoodbServiceTest {

    private Moodb moodb;
    private LoginRequest user;

    @MockBean
    private LoginService loginService;

    @Autowired
    private MoodbService moodbService;

     private Long id;

    @BeforeEach
    void setUp() {
        moodb =  new Moodb(2L, "rafael", "nao é",null);
        user = new LoginRequest( "rafael", "123");
        Auth.getUsers().add(new User(2L,"123","rafael","qualquertoken"));
         id = 1L;

    }

    @Test
    void insertShouldReturnMoodbAndStatusCode201() {
        when(loginService.login(user)).thenReturn( LoginResponse.builder().token("qualquertoken").build());
        assertEquals(201,moodbService.insertMoodb(moodb,loginService.login(user).getToken()).getStatusCode());

    }

    @Test
    void whenInsetByMoodbShouldReturn401WhenTokenInvalid() {


        assertEquals(401,moodbService.insertMoodb(moodb,"dasdasdas").getStatusCode());
    }

    @Test
    void whenInsetByMoodbShouldReturn40WhenTokenNotExist() {
        assertEquals(400,moodbService.insertMoodb(moodb,null).getStatusCode());

    }

    @Test
    void findShouldReturnMoodbWhenIdExists(){
        assertTrue(MoodbList.getMoodb().containsKey(1L));
    }
}