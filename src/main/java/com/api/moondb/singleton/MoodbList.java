package com.api.moondb.singleton;

import com.api.moondb.context.designermoodb.Moodb;

import java.util.HashSet;
import java.util.Set;

public class MoodbList {

    private static Set<Moodb> moodbs;

    public static Set<Moodb> getMoodb() {
        if(moodbs == null) {
            moodbs = new HashSet<>();
            moodbs.add(
                    new Moodb(1L, "rafael", "nao é",null));

        }
        return moodbs;
    }
}
