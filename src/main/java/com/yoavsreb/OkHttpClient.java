package com.yoavsreb;

import org.glassfish.jersey.uri.internal.JerseyUriBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Map;

/**
 * A bridge/adapter between OkHttp3 to JAX-RS interfaces.
 * Only the following flows are supported:
 *  Client::target
 *
 * Created by yoav on 3/29/19.
 */
public class OkHttpClient implements Client {
    final okhttp3.OkHttpClient client;
    final BridgeSerDe.Serializer serialializer;
    final BridgeSerDe.Deserializer deserializer;

    public OkHttpClient(okhttp3.OkHttpClient client) {
        this(client, BridgeSerDe.DEFAULT_SERIALIZER, BridgeSerDe.DEFAULT_DESERIALIZER);
    }

    public OkHttpClient(okhttp3.OkHttpClient client, BridgeSerDe.Serializer serialializer, BridgeSerDe.Deserializer deserializer) {
        this.client = client;
        this.serialializer = serialializer;
        this.deserializer = deserializer;
    }
    public BridgeSerDe.Serializer getSerialializer() {
        return serialializer;
    }

    public BridgeSerDe.Deserializer getDeserializer() {
        return deserializer;
    }

    public void close() {
        //No-Op
    }

    public WebTarget target(String s) {
        return new BridgeWebTarget(JerseyUriBuilder.fromPath(s), this);
    }

    public WebTarget target(URI uri) {
        return new BridgeWebTarget(JerseyUriBuilder.fromUri(uri), this);
    }

    public WebTarget target(UriBuilder uriBuilder) {
        return new BridgeWebTarget(uriBuilder, this);
    }

    public WebTarget target(Link link) {
        return new BridgeWebTarget(JerseyUriBuilder.fromLink(link), this);
    }

    public Invocation.Builder invocation(Link link) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public SSLContext getSslContext() {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public HostnameVerifier getHostnameVerifier() {
        throw new NotImplementedException();
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
    public Client property(String s, Object o) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public Client register(Class<?> aClass) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public Client register(Class<?> aClass, int i) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public Client register(Class<?> aClass, Class<?>... classes) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public Client register(Class<?> aClass, Map<Class<?>, Integer> map) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public Client register(Object o) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public Client register(Object o, int i) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public Client register(Object o, Class<?>... classes) {
        throw new NotImplementedException();
    }

    /**
     * Not implemented.
     */
    public Client register(Object o, Map<Class<?>, Integer> map) {
        throw new NotImplementedException();
    }


}
