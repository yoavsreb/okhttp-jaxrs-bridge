package com.yoavsreb.okhttpjaxrs;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Default Serializer/Deserializer using Google's Gson library.
 */
public final class GsonSerializerDeserializer implements Serializer, Deserializer {
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
