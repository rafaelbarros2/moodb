package com.api.moondb.context.designermoodb.services;

import com.api.moondb.context.designermoodb.model.Moodb;
import com.api.moondb.context.auth.model.User;
import com.api.moondb.context.designermoodb.model.response.MoodbResponse;
import com.api.moondb.singleton.Auth;
import com.api.moondb.singleton.MoodbList;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MoodbService {

    public MoodbResponse insertMoodb(Moodb moodb, String token) {
        if(moodb != null && token != null ) {
            if(verifyToken(token)) {
                moodb.setIdUser(returnUser(token).getId());
                MoodbList.getMoodb().put(newId(), moodb);
                return verifyCreate(token);
            }else {
                return MoodbResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode((short) 403)
                        .error("Acesso Negado")
                        .build();
            }
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

    public MoodbResponse findAll(String token){
        if(token != null && verifyToken(token)) {
            return MoodbResponse.builder()
                    .moodbList(filterMoodb(returnUser(token)))
                    .timestamp(LocalDateTime.now())
                    .statusCode((short) 200)
                    .build();
        }
        return MoodbResponse.builder()
                .timestamp(LocalDateTime.now())
                .statusCode((short) 403)
                .error("Não Autorizado")
                .build();
    }


    public MoodbResponse verifyCreate(String hash) {
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

   private User returnUser(String token){
       for(User user : Auth.getUsers()) {
           if(token.equals(user.getHash()))
               return user;
       }
       return null;
   }

   private List<Moodb> filterMoodb(User user){
       return MoodbList.getMoodb()
               .values().stream()
               .filter(moodb ->
                       moodb.getIdUser().equals(user.getId()))
               .collect(Collectors.toList());
   }

   private boolean verifyToken(String token){
       for(User user : Auth.getUsers()) {
           if(Objects.nonNull(user.getHash()))
               return user.getHash().equals(token);
       }
       return false;
   }
}
