package com.chanchuan.frame;

import java.util.HashMap;

public class ParamsHashMap extends HashMap<String, Object> {
    public ParamsHashMap add(String key, Object value) {
        this.put(key, value);
        return this;
    }
}
