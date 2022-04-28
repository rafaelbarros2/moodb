package com.api.moondb.singleton;

import com.api.moondb.context.designermoodb.model.Moodb;

import java.util.HashMap;
import java.util.Map;

public class MoodbList {

    private static Map<Long,Moodb> moodbs;
    private static Long id;


    public static Map<Long, Moodb> getMoodb() {
        if(moodbs == null) {
            moodbs =  new HashMap<>();
            id = (long) (moodbs.size() + 1);
            moodbs.put( id ,new Moodb(id, "rafael", "nao é",null,1L));

        }
        return moodbs;
    }
}
