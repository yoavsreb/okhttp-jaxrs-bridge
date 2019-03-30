package com.yoavsreb.okhttpjaxrs;

import okhttp3.OkHttpClient;

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
 *
  Client::Client jaxrsClient = new OkHttpBridgeClient(new OkHttpClient());
  String s = jaxrsClient.target("http://localhost:8080")
        .path("hello-world")
        .request("application/json")
        .get()
        .readEntity(String.class);
 *
 * Notice all calls to Client::register will fail with <b>NotImplementedException</b> - all
 * configurations should be done directly over the OkHttp client passed in.
 */
public class OkHttpBridgeClient implements Client {
    final okhttp3.OkHttpClient client;
    final Serializer serializer;
    final Deserializer deserializer;
    final UriBuilderFactory uriBuilderFactory;

    public static OkHttpBridgeClient newInstance(OkHttpClient client) {
        GsonSerializerDeserializer serializerDeserializer = new GsonSerializerDeserializer();
        return new OkHttpBridgeClient(client, serializerDeserializer, serializerDeserializer, new JerseyUriBuilderFactory());
    }

    public static OkHttpBridgeClient newInstance(okhttp3.OkHttpClient client, Serializer serializer, Deserializer deserializer,
                                                 UriBuilderFactory uriBuilderFactory) {
        return new OkHttpBridgeClient(client, serializer, deserializer, uriBuilderFactory);
    }

    OkHttpBridgeClient(okhttp3.OkHttpClient client, Serializer serializer, Deserializer deserializer,
                              UriBuilderFactory uriBuilderFactory) {
        this.client = client;
        this.serializer = serializer;
        this.deserializer = deserializer;
        this.uriBuilderFactory = uriBuilderFactory;
    }
    public Serializer getSerializer() {
        return serializer;
    }

    public Deserializer getDeserializer() {
        return deserializer;
    }

    public UriBuilderFactory getUriBuilderFactory() {
        return uriBuilderFactory;
    }
    public void close() {
        //No-Op
    }

    public WebTarget target(String s) {
        return new BridgeWebTarget(uriBuilderFactory.fromPath(s), this);
    }

    public WebTarget target(URI uri) {
        return new BridgeWebTarget(uriBuilderFactory.fromUri(uri), this);
    }

    public WebTarget target(UriBuilder uriBuilder) {
        return new BridgeWebTarget(uriBuilder, this);
    }

    public WebTarget target(Link link) {
        return new BridgeWebTarget(uriBuilderFactory.fromLink(link), this);
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
