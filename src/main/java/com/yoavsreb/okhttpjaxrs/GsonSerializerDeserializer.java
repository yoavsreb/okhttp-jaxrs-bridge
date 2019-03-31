package com.yoavsreb.okhttpjaxrs;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Default Serializer/Deserializer using Google's Gson library.
 * Assumes Content-Encoding is application/json with UTF8 encoding.
 */
public final class GsonSerializerDeserializer implements Serializer, Deserializer {
    Gson gson = new Gson();

    public String toString(Object o, String mediaType) {
        if (!mediaType.startsWith("application/json")) {
            throw new NotImplementedException();
        }
        return gson.toJson(o);
                                                             }

    public <T> T fromString(String s, String mediaType, Class<T> tClass) {
        if (!mediaType.startsWith("application/json")) {
            throw new NotImplementedException();
        }
        return gson.fromJson(s, tClass);
    }

    public <T> T fromString(String s, String mediaType, Type type) {
        if (!mediaType.startsWith("application/json")) {
            throw new NotImplementedException();
        }
        return gson.fromJson(s, type);
    }
}
