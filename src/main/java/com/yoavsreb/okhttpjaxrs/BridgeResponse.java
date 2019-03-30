package com.yoavsreb.okhttpjaxrs;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * TODO: implement missing functions.
 */
public class BridgeResponse extends Response {
    final okhttp3.Response response;
    final OkHttpBridgeClient client;

    BridgeResponse(okhttp3.Response response, OkHttpBridgeClient client) {
        this.response = response;
        this.client = client;
    }
    @Override
    public int getStatus() {
        return response.code();
    }

    @Override
    public StatusType getStatusInfo() {
        return Status.fromStatusCode(response.code());
    }

    @Override
    public Object getEntity() {
        return null;
    }

    @Override
    public <T> T readEntity(Class<T> aClass) {

        try {
            if (aClass.equals(String.class)) {
                return (T) response.body().string();
            }
            return client.getDeserializer().fromString(response.body().string(), aClass);
        } catch (IOException ex) {
            throw new ProcessingException(ex);
        }
    }

    @Override
    public <T> T readEntity(GenericType<T> genericType) {
        try {
            return client.getDeserializer().fromString(response.body().string(), genericType.getType());
        } catch (IOException ex) {
            throw new ProcessingException(ex);
        }
    }

    @Override
    public <T> T readEntity(Class<T> aClass, Annotation[] annotations) {
        return null;
    }

    @Override
    public <T> T readEntity(GenericType<T> genericType, Annotation[] annotations) {
        return null;
    }

    @Override
    public boolean hasEntity() {
        return (response.body() != null);
    }

    @Override
    public boolean bufferEntity() {
        return false;
    }

    @Override
    public void close() {
        response.close();
    }

    @Override
    public MediaType getMediaType() {
        return MediaType.valueOf(response.body().contentType().type());
    }

    @Override
    public Locale getLanguage() {
        return null;
    }

    @Override
    public int getLength() {
        return (int)response.body().contentLength();
    }

    @Override
    public Set<String> getAllowedMethods() {
        return null;
    }

    @Override
    public Map<String, NewCookie> getCookies() {
        return null;
    }

    @Override
    public EntityTag getEntityTag() {
        return null;
    }

    @Override
    public Date getDate() {
        return Date.from(Instant.ofEpochMilli(response.receivedResponseAtMillis()));
    }

    @Override
    public Date getLastModified() {
        return null;
    }

    @Override
    public URI getLocation() {
        return null;
    }

    @Override
    public Set<Link> getLinks() {
        return null;
    }

    @Override
    public boolean hasLink(String s) {
        return false;
    }

    @Override
    public Link getLink(String s) {
        return null;
    }

    @Override
    public Link.Builder getLinkBuilder(String s) {
        return null;
    }

    @Override
    public MultivaluedMap<String, Object> getMetadata() {
        return null;
    }

    @Override
    public MultivaluedMap<String, String> getStringHeaders() {
        MultivaluedHashMap<String, String> returnValue = new MultivaluedHashMap<>();
        for(String name : response.headers().names()) {
            returnValue.addAll(name, response.headers(name));
        }
        return returnValue;
    }

    @Override
    public String getHeaderString(String s) {
        return response.header(s);
    }
}
