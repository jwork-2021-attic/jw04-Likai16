package com.anish.calabashbros;

import java.util.ArrayList;
import java.util.Random;

public class Location {
    static final int count = 64;
    static ArrayList<Integer> list = new ArrayList<>(count);

    static {
        Random r = new Random(); 
        int i = 0;
        while (i < count) {
            int tmp = r.nextInt(count);
            if (!list.contains(tmp)) {
                list.add(tmp);
                i++;
            }
        }
    }

    public static int get(int i) {
        return list.get(i);
    }

}

