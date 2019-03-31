package com.yoavsreb.okhttpjaxrs;

import java.lang.reflect.Type;

public interface Deserializer {
    <T> T fromString(String s, String mediaType,  Class<T> tClass);
    <T> T fromString(String s, String mediaType, Type type);
}
