package io.swagger.api.factories;

import io.swagger.api.EventApiService;
import io.swagger.api.impl.EventApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-19T09:33:27.588Z")
public class EventApiServiceFactory {
    private final static EventApiService service = new EventApiServiceImpl();

    public static EventApiService getEventApi() {
        return service;
    }
}
