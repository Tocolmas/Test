package io.swagger.api.factories;

import io.swagger.api.InventionApiService;
import io.swagger.api.impl.InventionApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-19T09:33:27.588Z")
public class InventionApiServiceFactory {
    private final static InventionApiService service = new InventionApiServiceImpl();

    public static InventionApiService getInventionApi() {
        return service;
    }
}
