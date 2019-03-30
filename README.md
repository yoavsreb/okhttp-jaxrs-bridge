# okhttp-jaxrs-bridge

This project is bridge/adapter between [OkHttp](https://github.com/square/okhttp) to [JAX-RS interfaces](https://github.com/jax-rs).

## Examples
```  
Client jaxrsClient = new OkHttpBridgeClient(new OkHttpClient());
String s = jaxrsClient.target("http://localhost:8080")
  .path("hello-world")
  .request("application/json")
  .get()
  .readEntity(String.class);
```
