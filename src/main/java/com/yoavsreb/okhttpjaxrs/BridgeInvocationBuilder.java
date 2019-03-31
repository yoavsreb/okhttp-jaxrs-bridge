package com.yoavsreb.okhttpjaxrs;


import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

public class BridgeInvocationBuilder implements Invocation.Builder {
    private final OkHttpBridgeClient client;
    private final okhttp3.Request.Builder builder = new Request.Builder();
    private boolean acceptInitialized = false;

    BridgeInvocationBuilder(URI uri, OkHttpBridgeClient httpClient) {
        try {
            this.builder.url(uri.toURL());
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException(ex);
        }
        this.client = httpClient;
    }

    BridgeInvocationBuilder(URI uri, OkHttpBridgeClient httpClient, MediaType... mediaType) {
        this(uri, httpClient);
        initializeAcceptHeader(Arrays.stream(mediaType).map(MediaType::toString).collect(Collectors.toList()));
    }

    BridgeInvocationBuilder(URI uri, OkHttpBridgeClient httpClient, String... mediaType) {
        this(uri, httpClient);
        initializeAcceptHeader(Arrays.asList(mediaType));
    }

    private void initializeAcceptHeader(List<String> mediaTypes) {
        Iterator<String> iter = mediaTypes.iterator();
        while(iter.hasNext()) {
            if (!acceptInitialized) {
                builder.header("Accept", iter.next());
                acceptInitialized = true;
            } else {
               builder.addHeader("Accept", iter.next());
            }
        }
    }

    public Invocation build(String s) {
        builder.method(s, null);
        return new BridgeInvocation(builder.build(), this.client);
    }

    public Invocation build(String s, Entity<?> entity) {
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse(entity.getMediaType().toString()), client.getSerializer().toString(entity.getEntity(), entity.getMediaType().toString()));
        builder.method(s, requestBody);
        return new BridgeInvocation(builder.build(), this.client);
    }

    public Invocation buildGet() {
        return build("GET");
    }

    public Invocation buildDelete() {
        return build("DELETE");
    }

    public Invocation buildPost(Entity<?> entity) {
        return build("POST", entity);
    }

    public Invocation buildPut(Entity<?> entity) {
        return build("PUT", entity);
    }

    public AsyncInvoker async() {
        return null;
    }

    public Invocation.Builder accept(String... strings) {
        initializeAcceptHeader(Arrays.asList(strings));
        return this;
    }

    public Invocation.Builder accept(MediaType... mediaTypes) {
        initializeAcceptHeader(Arrays.stream(mediaTypes).map(MediaType::toString).collect(Collectors.toList()));
        return this;
    }

    public Invocation.Builder acceptLanguage(Locale... locales) {
        return header("accept-language", Arrays.stream(locales).map(Locale::toString).collect(Collectors.toList()));
    }

    public Invocation.Builder acceptLanguage(String... strings) {
        return header("accept-language", Arrays.asList(strings));
    }

    public Invocation.Builder acceptEncoding(String... strings) {
        return header("accept-encoding", Arrays.asList(strings));
    }

    public Invocation.Builder cookie(Cookie cookie) {
        throw new NotImplementedException();
    }
    public Invocation.Builder cookie(String s, String s1) {
        throw new NotImplementedException();
    }

    public Invocation.Builder cacheControl(CacheControl cacheControl) {
        throw new NotImplementedException();
    }

    public Invocation.Builder header(String s, Object o) {
        Object valueToWorkOn = o;
        if (valueToWorkOn instanceof Array) {
            valueToWorkOn = Arrays.asList((Array)o);
        }
        if (valueToWorkOn instanceof Collection) {
            Collection<?> collection = (Collection<?>) valueToWorkOn;
            Iterator<?> iter = collection.iterator();
            if (iter.hasNext()) {
                builder.header(s, iter.next().toString());
            }
            while(iter.hasNext()) {
                builder.addHeader(s, iter.next().toString());
            }
        } else {
            builder.header(s, valueToWorkOn.toString());
        }
        return this;
    }

    public Invocation.Builder headers(MultivaluedMap<String, Object> multivaluedMap) {
        for(String s : multivaluedMap.keySet()) {
            builder.removeHeader(s);
            header(s, multivaluedMap.get(s));
        }
        return this;
    }

    public Invocation.Builder property(String s, Object o) {
        throw new NotImplementedException();
    }

    public CompletionStageRxInvoker rx() {
        throw new NotImplementedException();
    }

    public <T extends RxInvoker> T rx(Class<T> aClass) {
        throw new NotImplementedException();
    }

    public Response get() {
        return method("GET");
    }

    public <T> T get(Class<T> aClass) {
        return method("GET", aClass);
    }

    public <T> T get(GenericType<T> genericType) {
        return method("GET", genericType);
    }

    public Response put(Entity<?> entity) {
        return method("PUT", entity);
    }

    public <T> T put(Entity<?> entity, Class<T> aClass) {
        return method("PUT", entity, aClass);
    }

    public <T> T put(Entity<?> entity, GenericType<T> genericType) {
        return method("PUT", entity, genericType);
    }

    public Response post(Entity<?> entity) {
        return method("POST", entity);
    }

    public <T> T post(Entity<?> entity, Class<T> aClass) {
        return method("POST", entity, aClass);
    }

    public <T> T post(Entity<?> entity, GenericType<T> genericType) {
        return method("POST", entity, genericType);
    }

    public Response delete() {
        return method("DELETE");
    }

    public <T> T delete(Class<T> aClass) {
        return method("DELETE", aClass);
    }

    public <T> T delete(GenericType<T> genericType) {
        return method("DELETE", genericType);
    }

    public Response head() {
        return method("HEAD");
    }

    public Response options() {
        return method("OPTIONS");
    }

    public <T> T options(Class<T> aClass) {
        return method("OPTIONS", aClass);
    }

    public <T> T options(GenericType<T> genericType) {
        return method("OPTIONS", genericType);
    }

    public Response trace() {
        return method("TRACE");
    }

    public <T> T trace(Class<T> aClass) {
        return method("TRACE", aClass);
    }

    public <T> T trace(GenericType<T> genericType) {
        return method("TRACE", genericType);
    }

    public Response method(String s) {
        return build(s).invoke();
    }

    public <T> T method(String s, Class<T> aClass) {
        return method(s).readEntity(aClass);
    }

    public <T> T method(String s, GenericType<T> genericType) {
        return method(s).readEntity(genericType);
    }

    public Response method(String s, Entity<?> entity) {
        return build(s, entity).invoke();
    }

    public <T> T method(String s, Entity<?> entity, Class<T> aClass) {
        return method(s, entity).readEntity(aClass);
    }

    public <T> T method(String s, Entity<?> entity, GenericType<T> genericType) {
        return method(s, entity).readEntity(genericType);
    }
}
