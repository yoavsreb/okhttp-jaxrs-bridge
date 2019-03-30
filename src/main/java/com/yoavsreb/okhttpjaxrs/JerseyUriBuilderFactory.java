package com.yoavsreb.okhttpjaxrs;

import org.glassfish.jersey.uri.internal.JerseyUriBuilder;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Default implementation that uses the Jersey URIBuilder.
 */
public class JerseyUriBuilderFactory implements UriBuilderFactory {

    @Override
    public UriBuilder fromLink(Link link) {
        return JerseyUriBuilder.fromLink(link);
    }

    @Override
    public UriBuilder fromPath(String s) {
        return JerseyUriBuilder.fromPath(s);
    }

    @Override
    public UriBuilder fromUri(URI uri) {
        return JerseyUriBuilder.fromUri(uri);
    }
}
