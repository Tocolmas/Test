package io.swagger.api.factories;

import io.swagger.api.InventorApiService;
import io.swagger.api.impl.InventorApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-05T22:21:06.922Z")
public class InventorApiServiceFactory {
    private final static InventorApiService service = new InventorApiServiceImpl();

    public static InventorApiService getInventorApi() {
        return service;
    }
}
