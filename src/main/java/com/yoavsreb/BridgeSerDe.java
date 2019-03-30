package com.yoavsreb;

import com.google.gson.Gson;

import javax.ws.rs.core.GenericType;
import java.lang.reflect.Type;

/**
 * Created by yoav on 3/29/19.
 */
public class BridgeSerDe {
    public interface Serializer {
        String toJsonString(Object o);
    }

    static {
        GsonSerializer instance =new GsonSerializer();
        DEFAULT_DESERIALIZER = instance;
        DEFAULT_SERIALIZER = instance;
    }

    public static final Serializer DEFAULT_SERIALIZER;
    public static final Deserializer DEFAULT_DESERIALIZER;

    public static class GsonSerializer implements Serializer, Deserializer {
        Gson gson = new Gson();

        public String toJsonString(Object o) {
            return gson.toJson(o);
        }

        public <T> T fromString(String s, Class<T> tClass) {
            return gson.fromJson(s, tClass);
        }

        public <T> T fromString(String s, Type type) {
            return gson.fromJson(s, type);
        }
    }

    public interface Deserializer {
        <T> T fromString(String s, Class<T> tClass);
        <T> T fromString(String s, Type type);
    }



}
