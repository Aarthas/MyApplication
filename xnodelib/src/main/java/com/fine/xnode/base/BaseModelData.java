package com.fine.xnode.base;

import java.lang.reflect.Field;
import java.util.HashMap;

public class BaseModelData {

    public static Object getByReflect(Object taget, String name) {
        try {

            Field declaredField = taget.getClass().getDeclaredField(name);
            declaredField.setAccessible(true);
            Object o = declaredField.get(taget);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
