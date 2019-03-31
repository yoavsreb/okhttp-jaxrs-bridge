# okhttp-jaxrs-bridge

This project is bridge/adapter between [OkHttp](https://github.com/square/okhttp) to [JAX-RS interfaces](https://github.com/jax-rs).

Notice this uses the following dependencies:
* OKHttp3 3.14.0
* Jax-RS specs 2.1 // For the interface definitions
* Google Gson 2.8.5  // Needed for the default Serializer/Deserializer
* Jersey-core 2.9  // Needed for the UriBuilder implementation

## Examples
```
Client jaxrsClient = OkHttpBridgeClient.newInstance(new OkHttpClient());
String s = jaxrsClient.target("http://localhost:8080")
  .path("hello-world")
  .request("application/json")
  .get()
  .readEntity(String.class);
```

## Usage
In maven:
``` <repositories>
           <repository>
               <id>mvn-repo</id>
               <url>https://rawgit.com/yoavsreb/artifacts/master</url>
               <releases>
                   <enabled>true</enabled>
               </releases>
               <snapshots>
                   <enabled>true</enabled>
               </snapshots>
           </repository>
       </repositories>
       <dependencies>
           <dependency>
               <groupId>com.yoavsreb</groupId>
               <artifactId>okhttpjaxrs</artifactId>
               <version>1.0-SNAPSHOT</version>
               <exclusions>
                   <exclusion>
                           <!-- If you already import a specific versrion -->
                           <groupId>org.glassfish.jersey.core</groupId>
                           <artifactId>jersey-common</artifactId>
                   </exclusion>
               </exclusions>
           </dependency>
       </dependencies>
```
