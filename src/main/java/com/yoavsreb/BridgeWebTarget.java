package com.yoavsreb;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Map;

/**
 * Created by yoav on 3/29/19.
 */
public class BridgeWebTarget implements WebTarget {
    private final UriBuilder uriBuilder;
    private final OkHttpBridgeClient okHttpBridgeClient;

    public BridgeWebTarget(UriBuilder builder, OkHttpBridgeClient httpClient) {
        this.uriBuilder = UriBuilder.fromUri(builder.build());
        this.okHttpBridgeClient = httpClient;
    }

    public URI getUri() {
        return uriBuilder.build();
    }

    public UriBuilder getUriBuilder() {
        return uriBuilder;
    }

    public WebTarget path(String s) {
        this.uriBuilder.path(s);
        return this;
    }

    /**
     * Not implemented.
     */
    public WebTarget resolveTemplate(String s, Object o) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public WebTarget resolveTemplate(String s, Object o, boolean b) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public WebTarget resolveTemplateFromEncoded(String s, Object o) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public WebTarget resolveTemplates(Map<String, Object> map) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public WebTarget resolveTemplates(Map<String, Object> map, boolean b) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public WebTarget resolveTemplatesFromEncoded(Map<String, Object> map) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public WebTarget matrixParam(String s, Object... objects) {
        throw new NotImplementedException();
    }

    public WebTarget queryParam(String s, Object... objects) {
        uriBuilder.queryParam(s, objects);
        return this;
    }

    public Invocation.Builder request() {
        return new BridgeInvocationBuilder(uriBuilder.build(), okHttpBridgeClient);

    }

    public Invocation.Builder request(String... strings) {
        return new BridgeInvocationBuilder(uriBuilder.build(), okHttpBridgeClient, strings);
    }

    public Invocation.Builder request(MediaType... mediaTypes) {
        return new BridgeInvocationBuilder(uriBuilder.build(), okHttpBridgeClient, mediaTypes);
    }

    /**
     * Not implemented.
     */
    public Configuration getConfiguration() {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public WebTarget property(String s, Object o) {
        return null;
    }

    /**
     * Not implemented.
     */
    public WebTarget register(Class<?> aClass) {
        return null;
    }

    /**
     * Not implemented.
     */
    public WebTarget register(Class<?> aClass, int i) {
        return null;
    }

    /**
     * Not implemented.
     */
    public WebTarget register(Class<?> aClass, Class<?>... classes) {
        return null;
    }

    /**
     * Not implemented.
     */
    public WebTarget register(Class<?> aClass, Map<Class<?>, Integer> map) {
        return null;
    }

    /**
     * Not implemented.
     */
    public WebTarget register(Object o) {
        return null;
    }

    /**
     * Not implemented.
     */
    public WebTarget register(Object o, int i) {
        return null;
    }

    /**
     * Not implemented.
     */
    public WebTarget register(Object o, Class<?>... classes) {
        return null;
    }

    /**
     * Not implemented.
     */
    public WebTarget register(Object o, Map<Class<?>, Integer> map) {
        return null;
    }
}
