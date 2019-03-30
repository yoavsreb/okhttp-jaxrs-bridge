package com.yoavsreb.okhttpjaxrs;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Encapsulates the UriBuilder creation.
 * Reason is Jax-RS vanilla implementation uses reflection
 * and currently only supports Jersey implementation.
 */
interface UriBuilderFactory {
    UriBuilder fromLink(Link link);

    UriBuilder fromPath(String s);

    UriBuilder fromUri(URI uri);
}
