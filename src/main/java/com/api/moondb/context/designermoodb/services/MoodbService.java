package com.api.moondb.context.designermoodb.services;

import com.api.moondb.context.designermoodb.Moodb;
import com.api.moondb.context.auth.User;
import com.api.moondb.singleton.Auth;
import com.api.moondb.singleton.MoodbList;
import org.springframework.stereotype.Service;

@Service
public class MoodbService {

    public String insertMoodb(Moodb moodb, String hash) {
        if(moodb != null && hash != null ) {
            MoodbList.getMoodb().add(moodb);
            System.out.println(MoodbList.getMoodb());
            return verify(hash) ;
        }
            return "400 Bad Request";
    }


    public String verify(String hash) {
        String link;
      for(User user : Auth.getUsers()) {
          link = user.getLogin() + "/" + user.getId().toString() + "/" + "enter";
          if(hash.equals(user.getHash()))
              return link;
      }

        return "401 Unauthorized";
    }
}
