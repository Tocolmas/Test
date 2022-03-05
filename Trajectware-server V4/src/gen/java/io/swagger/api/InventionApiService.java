package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.File;
import io.swagger.model.Invention;
import io.swagger.model.ModelApiResponse;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")
public abstract class InventionApiService {
    public abstract Response addInvention(Invention body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteInvention(Long inventionId,String apiKey,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findByDate( String date,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findInventionByInventor( @NotNull List<String> inventor,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findInventionBysByTags( @NotNull List<String> tags, String date,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findInventionsByName( @NotNull String name,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findInventionsByStatus( @NotNull String status,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getInventionById(Long inventionId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateInvention(Invention body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateInventionWithForm(Long inventionId,String name,String status,SecurityContext securityContext) throws NotFoundException;
    public abstract Response uploadFile(Long inventionId,String additionalMetadata,InputStream fileInputStream, FormDataContentDisposition fileDetail,SecurityContext securityContext) throws NotFoundException;
}
