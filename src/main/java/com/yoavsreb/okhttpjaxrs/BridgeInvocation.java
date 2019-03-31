package com.yoavsreb.okhttpjaxrs;

import okhttp3.Request;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.concurrent.Future;

public class BridgeInvocation implements Invocation {
    private final Request request;
    private final OkHttpBridgeClient okHttpBridgeClient;

    BridgeInvocation(Request request, OkHttpBridgeClient httpClient) {
        this.request = request;
        this.okHttpBridgeClient = httpClient;
    }

    public Request getRequest() {
        return request;
    }

    public Invocation property(String s, Object o) {
        throw new NotImplementedException();
    }

    public Response invoke() {
        try {
            return new BridgeResponse(okHttpBridgeClient.client.newCall(request).execute(), okHttpBridgeClient);
        } catch (IOException ex) {
            throw new ProcessingException(ex);
        }
    }

    public <T> T invoke(Class<T> aClass) {
        return invoke().readEntity(aClass);
    }

    public <T> T invoke(GenericType<T> genericType) {
        return invoke().readEntity(genericType);
    }

    public Future<Response> submit() {
        throw new NotImplementedException();
    }

    public <T> Future<T> submit(Class<T> aClass) {
        throw new NotImplementedException();
    }

    public <T> Future<T> submit(GenericType<T> genericType) {
        throw new NotImplementedException();
    }

    public <T> Future<T> submit(InvocationCallback<T> invocationCallback) {
        throw new NotImplementedException();
    }


}
