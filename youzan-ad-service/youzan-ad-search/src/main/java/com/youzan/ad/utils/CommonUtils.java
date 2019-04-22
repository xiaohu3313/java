package com.youzan.ad.utils;

import java.util.Map;
import java.util.function.Supplier;

public class CommonUtils {
    public static <K,V> V getorCreate(K key, Map<K,V> map,
                                      Supplier<V> supplier){
        return map.computeIfAbsent(key,
                k -> supplier.get() );

    }

    public static String stringConcat(String... args){
        StringBuilder builder = new StringBuilder();
        for (String arg:args){
            builder.append(arg);
            builder.append("-");
        }
        return builder.deleteCharAt(builder.length()-1).toString();
    }
}
