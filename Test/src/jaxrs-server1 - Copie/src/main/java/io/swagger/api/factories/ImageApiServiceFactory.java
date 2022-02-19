package io.swagger.api.factories;

import io.swagger.api.ImageApiService;
import io.swagger.api.impl.ImageApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-19T09:33:27.588Z")
public class ImageApiServiceFactory {
    private final static ImageApiService service = new ImageApiServiceImpl();

    public static ImageApiService getImageApi() {
        return service;
    }
}
