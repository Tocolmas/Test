package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.File;
import io.swagger.model.Inventor;
import io.swagger.model.ModelApiResponse;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")
public abstract class InventorApiService {
    public abstract Response addInventor(Inventor inventor,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteInventor(Long inventorId,String apiKey,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findInventorByInvention( @NotNull List<String> invention,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findInventorsByName( @NotNull String name,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findInventorsByStatus( @NotNull String status,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getInventorById(Long inventorId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response inventorfindByDate( String date,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateInventor(Inventor inventor,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateInventorWithForm(Long inventorId,String name,String status,SecurityContext securityContext) throws NotFoundException;
    public abstract Response uploadImage(Long inventorId,String additionalMetadata,InputStream fileInputStream, FormDataContentDisposition fileDetail,SecurityContext securityContext) throws NotFoundException;
}
