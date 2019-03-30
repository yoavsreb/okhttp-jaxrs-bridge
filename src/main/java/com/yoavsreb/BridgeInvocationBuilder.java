package com.yoavsreb;


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

/**
 * Created by yoav on 3/29/19.
 */
public class BridgeInvocationBuilder implements Invocation.Builder {
    private final OkHttpClient client;
    private final okhttp3.Request.Builder builder = new Request.Builder();
    private boolean acceptInitialized = false;

    BridgeInvocationBuilder(URI uri, OkHttpClient httpClient) {
        try {
            this.builder.url(uri.toURL());
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException(ex);
        }
        this.client = httpClient;
    }

    BridgeInvocationBuilder(URI uri, OkHttpClient httpClient, MediaType... mediaType) {
        this(uri, httpClient);
        initializeAcceptHeader(Arrays.stream(mediaType).map(MediaType::toString).collect(Collectors.toList()));
    }

    BridgeInvocationBuilder(URI uri, OkHttpClient httpClient, String... mediaType) {
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
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse(entity.getMediaType().toString()), client.getSerialializer().toJsonString(entity.getEntity()));
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
        return null;
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
        return null;
    }

    public Invocation.Builder cookie(String s, String s1) {
        return null;
    }

    public Invocation.Builder cacheControl(CacheControl cacheControl) {
        return null;
    }

    public Invocation.Builder header(String s, Object o) {
        Object valueToWorkOn = o;
        if (valueToWorkOn instanceof Array) {
            valueToWorkOn = Arrays.asList((Array)o);
        }
        if (valueToWorkOn instanceof Collection) {
            Collection<?> collection = (Collection<?>) valueToWorkOn;
            Iterator<?> iter = collection.iterator();
            boolean isFirstDone = false;
            while(iter.hasNext()) {
                if (isFirstDone) {
                    builder.addHeader(s, iter.next().toString());
                } else {
                    builder.header(s, iter.next().toString());
                    isFirstDone = true;
                }
            }
        } else {
            builder.header(s, valueToWorkOn.toString());
        }
        return this;
    }

    public Invocation.Builder headers(MultivaluedMap<String, Object> multivaluedMap) {
        return null;
    }

    public Invocation.Builder property(String s, Object o) {
        return null;
    }

    public CompletionStageRxInvoker rx() {
        return null;
    }

    public <T extends RxInvoker> T rx(Class<T> aClass) {
        return null;
    }

    public Response get() {
        return new BridgeInvocation(builder.build(), this.client).invoke();
    }

    public <T> T get(Class<T> aClass) {
        return new BridgeInvocation(builder.build(), this.client).invoke().readEntity(aClass);
    }

    public <T> T get(GenericType<T> genericType) {
        return null;
    }

    public Response put(Entity<?> entity) {
        return null;
    }

    public <T> T put(Entity<?> entity, Class<T> aClass) {
        return null;
    }

    public <T> T put(Entity<?> entity, GenericType<T> genericType) {
        return null;
    }

    public Response post(Entity<?> entity) {
        builder.post(RequestBody.create(okhttp3.MediaType.parse(entity.getMediaType().toString()), client.getSerialializer().toJsonString(entity.getEntity())));
        return new BridgeInvocation(builder.build(), this.client).invoke();
    }

    public <T> T post(Entity<?> entity, Class<T> aClass) {
        return null;
    }

    public <T> T post(Entity<?> entity, GenericType<T> genericType) {
        return null;
    }

    public Response delete() {
        return null;
    }

    public <T> T delete(Class<T> aClass) {
        return null;
    }

    public <T> T delete(GenericType<T> genericType) {
        return null;
    }

    public Response head() {
        return null;
    }

    public Response options() {
        return null;
    }

    public <T> T options(Class<T> aClass) {
        return null;
    }

    public <T> T options(GenericType<T> genericType) {
        return null;
    }

    public Response trace() {
        return null;
    }

    public <T> T trace(Class<T> aClass) {
        return null;
    }

    public <T> T trace(GenericType<T> genericType) {
        return null;
    }

    public Response method(String s) {
        return null;
    }

    public <T> T method(String s, Class<T> aClass) {
        return null;
    }

    public <T> T method(String s, GenericType<T> genericType) {
        return null;
    }

    public Response method(String s, Entity<?> entity) {
        return null;
    }

    public <T> T method(String s, Entity<?> entity, Class<T> aClass) {
        return null;
    }

    public <T> T method(String s, Entity<?> entity, GenericType<T> genericType) {
        return null;
    }
}
