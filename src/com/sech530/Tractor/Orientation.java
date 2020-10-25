package com.sech530.Tractor;

import java.util.HashMap;
import java.util.Map;

public enum Orientation {
    NORTH, WEST, SOUTH, EAST;

    private static Map<Orientation,Orientation> map = new HashMap<>();

    static {
        map.put(NORTH,EAST);
        map.put(EAST, SOUTH);
        map.put(SOUTH,WEST);
        map.put(WEST,NORTH);
    }

    public Orientation turnClockwise(Orientation orientation){
        return map.get(orientation);
    }
}
