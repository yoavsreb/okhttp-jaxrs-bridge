package com.yoavsreb.okhttpjaxrs;

/**
 * Created by yoav on 3/29/19.
 */
public class NotImplementedException extends RuntimeException {
    public NotImplementedException() {
        super("Not implemented flow. See documentation for more details");
    }
}
