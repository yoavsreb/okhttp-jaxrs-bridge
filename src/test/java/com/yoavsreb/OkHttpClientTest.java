package com.yoavsreb;

import okhttp3.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
import java.util.Locale;

/**
 * Created by yoav on 3/29/19.
 */
public class OkHttpClientTest {
    @Test
    public void testGet() {
        okhttp3.OkHttpClient client = new OkHttpClient();
        Client bridge = new com.yoavsreb.OkHttpClient(client);
        String s = bridge.target("http://localhost:8080").path("hello-world").request("application/json").get().readEntity(String.class);
        System.out.println(s);
    }

    public static class SimpleClass1 {
        private int i;
        private String s;
        private long[] arr;


        public SimpleClass1(int i, String s, long[] arr) {
            this.i = i;
            this.s = s;
            this.arr = arr;
        }

        public int getI() {
            return i;
        }

        public String getS() {
            return s;
        }

        public long[] getArr() {
            return arr;
        }
    }


    @Test
    public void testGetHeadersJsonObject() {
        okhttp3.OkHttpClient client = new OkHttpClient();
        Client bridge = new com.yoavsreb.OkHttpClient(client);
        javax.ws.rs.core.Response response = bridge.target("http://localhost:8080").path("hello-world").path("object")
                .request("application/json").header("x-yoav", "headerValue").get();
        SimpleClass1 simpleClass1 = response.readEntity(SimpleClass1.class);
        System.out.println(response.getHeaderString("x-yoav-returned"));
        System.out.println(simpleClass1);
    }

    public static class MyMessage {
        public MyMessage() {

        }

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }
    @Test
    public void testPostHeadersJsonObject() {
        okhttp3.OkHttpClient client = new OkHttpClient();
        Client bridge = new com.yoavsreb.OkHttpClient(client);
        MyMessage msg = new MyMessage();
        msg.setAge(5);
        msg.setName("Mika");
        javax.ws.rs.core.Response response = bridge.target("http://localhost:8080").path("hello-world")
                .request("application/json").header("x-yoav", "headerValue").post(Entity.entity(msg, "application/json"));
        System.out.println(response.readEntity(String.class));
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testUriBuilding() {
        okhttp3.OkHttpClient client = null;
        Client bridge = new com.yoavsreb.OkHttpClient(client);
        BridgeInvocation.Builder invoc = bridge.target("http://localhost:8080").path("hello-world")
                .queryParam("q", "queryParam1")
                .request("application/json").header("x-yoav", "headerValue");

        BridgeInvocation invo = (BridgeInvocation) invoc.build("application/json");
    }

    @Test
    public void testBuilderHeaders() {
        okhttp3.OkHttpClient client = null;
        Client bridge = new com.yoavsreb.OkHttpClient(client);
        BridgeInvocation.Builder invoc = bridge.target("http://localhost:8080").path("hello-world")
                .queryParam("q", "queryParam1")
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .acceptLanguage(Locale.US)
                .acceptEncoding("gzip", "Deflate")
                .header("x-yoav", "headerValue");
        Request request = ((BridgeInvocation)invoc.buildGet()).getRequest();
        Headers headers = request.headers();
        System.out.println(headers.getInstant("accept"));

        System.out.println(request);
    }


}
