package com.api.moondb.singleton;

import com.api.moondb.context.auth.model.User;

import java.util.ArrayList;
import java.util.List;

public class Auth {

    private static List<User> users;

    public static List<User> getUsers() {
        if(users == null) {
            users = new ArrayList<>();
            users.add(new User(1L, "rafael", "123",null));

        }
        return users;
    }
}
