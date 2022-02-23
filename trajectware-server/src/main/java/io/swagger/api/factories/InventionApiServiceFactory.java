package io.swagger.api.factories;

import io.swagger.api.InventionApiService;
import io.swagger.api.impl.InventionApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-21T11:04:43.972Z")
public class InventionApiServiceFactory {
    private final static InventionApiService service = new InventionApiServiceImpl();

    public static InventionApiService getInventionApi() {
        return service;
    }
}
