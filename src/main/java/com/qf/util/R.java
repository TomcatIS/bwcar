package com.qf.util;

import java.util.HashMap;

public class R extends HashMap<String, Object> {
    public  R(){
        put("code", 0);
    }
    public R put(String key, Object value){
        super.put(key, value);
        return this;
    }
}
