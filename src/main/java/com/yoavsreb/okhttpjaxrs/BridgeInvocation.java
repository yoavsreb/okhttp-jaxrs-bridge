package com.yoavsreb.okhttpjaxrs;

import okhttp3.Request;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Created by yoav on 3/29/19.
 */
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
        return null;
    }

    public Response invoke() {
        try {
            return new BridgeResponse(okHttpBridgeClient.client.newCall(request).execute(), okHttpBridgeClient);
        } catch (IOException ex) {
            throw new ProcessingException(ex);
        }
    }

    public <T> T invoke(Class<T> aClass) {
        return null;
    }

    public <T> T invoke(GenericType<T> genericType) {
        return null;
    }

    public Future<Response> submit() {
        return null;
    }

    public <T> Future<T> submit(Class<T> aClass) {
        return null;
    }

    public <T> Future<T> submit(GenericType<T> genericType) {
        return null;
    }

    public <T> Future<T> submit(InvocationCallback<T> invocationCallback) {
        return null;
    }


}
