package io.swagger.api.factories;

import io.swagger.api.InventorApiService;
import io.swagger.api.impl.InventorApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")
public class InventorApiServiceFactory {
    private final static InventorApiService service = new InventorApiServiceImpl();

    public static InventorApiService getInventorApi() {
        return service;
    }
}
