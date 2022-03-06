package io.swagger.api.factories;

import io.swagger.api.EventApiService;
import io.swagger.api.impl.EventApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-05T22:21:06.922Z")
public class EventApiServiceFactory {
    private final static EventApiService service = new EventApiServiceImpl();

    public static EventApiService getEventApi() {
        return service;
    }
}
