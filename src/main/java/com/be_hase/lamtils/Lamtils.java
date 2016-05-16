package com.be_hase.lamtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Lamtils {
    public static <T extends Map> Map<String, Object> newMap(Class<T> clazz, KeyValueFunction<Object>... keyValuePairs) {
        try {
            Map<String, Object> map = clazz.newInstance();
            Arrays.stream(keyValuePairs)
                    .forEach(kvp -> map.put(kvp.key(), kvp.value()));
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Object> newHashMap(KeyValueFunction<Object>... keyValuePairs) {
        return newMap(HashMap.class, keyValuePairs);
    }

    public static Map<String, Object> newLinkedHashMap(KeyValueFunction<Object>... keyValuePairs) {
        return newMap(LinkedHashMap.class, keyValuePairs);
    }

    public static Map<String, Object> newTreeMap(KeyValueFunction<Object>... keyValuePairs) {
        return newMap(TreeMap.class, keyValuePairs);
    }

    public static Map<String, Object> newConcurrentMap(KeyValueFunction<Object>... keyValuePairs) {
        return newMap(ConcurrentHashMap.class, keyValuePairs);
    }

    public static <T> T newClass(Class<T> clazz, KeyValueFunction<Object>... keyValuePairs) {
        try {
            T obj = clazz.newInstance();
            Arrays.stream(keyValuePairs)
                    .forEach(kvp -> {
                        try {
                            PropertyUtils.setProperty(obj, kvp.key(), kvp.value());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
