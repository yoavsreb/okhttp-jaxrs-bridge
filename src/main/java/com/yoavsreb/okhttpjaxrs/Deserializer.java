package com.yoavsreb.okhttpjaxrs;

import java.lang.reflect.Type;

public interface Deserializer {
    <T> T fromString(String s, Class<T> tClass);
    <T> T fromString(String s, Type type);
}
