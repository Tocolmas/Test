package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ImageApiService;
import io.swagger.api.factories.ImageApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;


import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/image")


@io.swagger.annotations.Api(description = "the image API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")
public class ImageApi  {
   private final ImageApiService delegate;

   public ImageApi(@Context ServletConfig servletContext) {
      ImageApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("ImageApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (ImageApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = ImageApiServiceFactory.getImageApi();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/{fileId}")
    
    @Produces({ "image/jpg" })
    @io.swagger.annotations.ApiOperation(value = "Find a file by its ID", notes = "Returns only one file", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "api_key")
    }, tags={ "image", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "the event has not been found", response = Void.class) })
    public Response getImageById(@ApiParam(value = "the ID of the image that need to be returned",required=true) @PathParam("fileId") Long fileId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getImageById(fileId,securityContext);
    }
}
