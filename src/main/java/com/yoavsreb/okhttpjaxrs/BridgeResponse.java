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
        throw new NotImplementedException();
    }

    @Override
    public <T> T readEntity(Class<T> aClass) {

        try {
            if (aClass.equals(String.class)) {
                return (T) response.body().string();
            }
            return client.getDeserializer().fromString(response.body().string(),
                    response.body().contentType().toString(), aClass);
        } catch (IOException ex) {
            throw new ProcessingException(ex);
        }
    }

    @Override
    public <T> T readEntity(GenericType<T> genericType) {
        try {
            return client.getDeserializer().fromString(response.body().string(),
                    response.body().contentType().toString(),
                    genericType.getType());
        } catch (IOException ex) {
            throw new ProcessingException(ex);
        }
    }

    @Override
    public <T> T readEntity(Class<T> aClass, Annotation[] annotations) {
        throw new NotImplementedException();
    }

    @Override
    public <T> T readEntity(GenericType<T> genericType, Annotation[] annotations) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasEntity() {
        return (response.body() != null);
    }

    @Override
    public boolean bufferEntity() {
        throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

    @Override
    public int getLength() {
        return (int)response.body().contentLength();
    }

    @Override
    public Set<String> getAllowedMethods() {
        throw new NotImplementedException();
    }

    @Override
    public Map<String, NewCookie> getCookies() {
        throw new NotImplementedException();
    }

    @Override
    public EntityTag getEntityTag() {
        String headerValue = response.header("etag");
        if (headerValue == null) {
            return null;
        } else {
            return EntityTag.valueOf(headerValue);
        }
    }

    @Override
    public Date getDate() {
        return Date.from(Instant.ofEpochMilli(response.receivedResponseAtMillis()));
    }

    @Override
    public Date getLastModified() {
        throw new NotImplementedException();
    }

    @Override
    public URI getLocation() {
        throw new NotImplementedException();
    }

    @Override
    public Set<Link> getLinks() {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasLink(String s) {
        throw new NotImplementedException();
    }

    @Override
    public Link getLink(String s) {
        throw new NotImplementedException();
    }

    @Override
    public Link.Builder getLinkBuilder(String s) {
        throw new NotImplementedException();
    }

    @Override
    public MultivaluedMap<String, Object> getMetadata() {
        throw new NotImplementedException();
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
