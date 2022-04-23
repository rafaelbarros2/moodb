package com.api.moondb.context.designermoodb.services;

import com.api.moondb.context.designermoodb.model.Moodb;
import com.api.moondb.context.auth.model.User;
import com.api.moondb.context.designermoodb.model.response.MoodbResponse;
import com.api.moondb.singleton.Auth;
import com.api.moondb.singleton.MoodbList;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MoodbService {

    public MoodbResponse insertMoodb(Moodb moodb, String hash) {
        if(moodb != null && hash != null ) {
            MoodbList.getMoodb().put(newId(),moodb);
            System.out.println(MoodbList.getMoodb());

            return verify(hash);
        }
        return MoodbResponse.builder()
                .timestamp(LocalDateTime.now())
                .statusCode((short) 400)
                .error("Token não informado")
                .build();
    }

    public MoodbResponse find(Long id){
        if(MoodbList.getMoodb().containsKey(id)) {
            return MoodbResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .statusCode((short) 200)
                    .moodb(MoodbList.getMoodb().get(id))
                    .build();
        }
        return MoodbResponse.builder()
                .timestamp(LocalDateTime.now())
                .statusCode((short) 404)
                .error("Moodb não encontrado")
                .build();
    }


    public MoodbResponse verify(String hash) {
        String link;
      for(User user : Auth.getUsers()) {
          link = "/" + user.getLogin() + "/" + user.getId().toString() + "/" + "enter";
          if(hash.equals(user.getHash()))
              return MoodbResponse.builder()
                      .timestamp(LocalDateTime.now())
                      .statusCode((short) 201)
                      .shareableLink(link)
                      .build();
      }

        return MoodbResponse.builder()
                .timestamp(LocalDateTime.now())
                .statusCode((short) 401)
                .build();
    }

    private Long newId(){
        return MoodbList.getMoodb().size() + 1L;
    }
}
