package com.api.moondb.context.designermoodb.services;

import com.api.moondb.context.designermoodb.model.Imagem;
import com.api.moondb.context.designermoodb.model.Moodb;
import com.api.moondb.context.designermoodb.model.response.ImagemResponse;
import com.api.moondb.context.designermoodb.model.response.MoodbResponse;
import com.api.moondb.singleton.MoodbList;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImagemService {

    public ImagemResponse saveComments(Long id, List<Imagem> comment) {
       if(MoodbList.getMoodb().get(id).getImagens() == null) return response("Imagem Não existe", (short) 404,null);
        if (id != null && comment != null ) {
            for (Imagem img : comment) {

                for (Imagem imagem : MoodbList.getMoodb().get(id).getImagens()) {
                    if (img.getId() == imagem.getId()) {
                        imagem.setComment(img.getComment());

                    }

                }
            }
            System.out.println( MoodbList.getMoodb().get(id).getImagens());
             return response(null, (short) 204, MoodbList.getMoodb().get(id).getImagens());
        }

        return response("Não foi Possivel fazer a alteração", (short) 400,null);

    }

    private ImagemResponse response(String msg,short statusCode, List<Imagem> imagems){
      return ImagemResponse.builder()
              .timestamp(LocalDateTime.now())
              .statusCode(statusCode)
              .error(msg)
              .imagens(imagems)
              .build();
    }

}
