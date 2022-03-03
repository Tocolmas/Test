package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.InventorApiService;
import io.swagger.api.factories.InventorApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import java.io.File;
import io.swagger.model.Inventor;
import io.swagger.model.ModelApiResponse;

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

@Path("/inventor")


@io.swagger.annotations.Api(description = "the inventor API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")
public class InventorApi  {
   private final InventorApiService delegate;

   public InventorApi(@Context ServletConfig servletContext) {
      InventorApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("InventorApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (InventorApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = InventorApiServiceFactory.getInventorApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add a new inventor in the dataBase", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "inventor_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:inventor", description = "modify inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:inventor", description = "read the inventors in the dataBase")
        })
    }, tags={ "inventor", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "The inventor you are trying to add already exists", response = Void.class) })
    public Response addInventor(@ApiParam(value = "The inventor that needs to be added in the dataBase" ,required=true) Inventor inventor
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addInventor(inventor,securityContext);
    }
    @DELETE
    @Path("/{inventorId}")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Deletes an inventor", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "inventor_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:inventor", description = "modify inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:inventor", description = "read the inventors in the dataBase")
        })
    }, tags={ "inventor", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class) })
    public Response deleteInventor(@ApiParam(value = "Inventor id that needs to be deleted",required=true) @PathParam("inventorId") Long inventorId
,@ApiParam(value = "" )@HeaderParam("api_key") String apiKey
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteInventor(inventorId,apiKey,securityContext);
    }
    @GET
    @Path("/findByInvention")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds inventors by their inventions", notes = "Allows to find inventors by their inventions", response = Inventor.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "inventor_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:inventor", description = "modify inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:inventor", description = "read the inventors in the dataBase")
        })
    }, tags={ "inventor", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Inventor.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid tag value", response = Void.class) })
    public Response findInventorByInvention(@ApiParam(value = "Descriptions to filter by",required=true) @QueryParam("invention") List<String> invention
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findInventorByInvention(invention,securityContext);
    }
    @GET
    @Path("/findByName")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds inventors by Name", notes = "the name of the inventor", response = Inventor.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "inventor_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:inventor", description = "modify inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:inventor", description = "read the inventors in the dataBase")
        })
    }, tags={ "inventor", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Inventor.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid status value", response = Void.class) })
    public Response findInventorsByName(@ApiParam(value = "",required=true) @QueryParam("name") String name
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findInventorsByName(name,securityContext);
    }
    @GET
    @Path("/findByStatus")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds inventors by status", notes = "the status values that we need to considere to filter (is the inventor dead or alive", response = Inventor.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "inventor_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:inventor", description = "modify inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:inventor", description = "read the inventors in the dataBase")
        })
    }, tags={ "inventor", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Inventor.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid status value", response = Void.class) })
    public Response findInventorsByStatus(@ApiParam(value = "the status values that we need to considere to filter (is the inventor dead or alive)",required=true) @QueryParam("status") String status
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findInventorsByStatus(status,securityContext);
    }
    @GET
    @Path("/{inventorId}")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Find an inventor by its ID", notes = "Returns only one inventor", response = Inventor.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "api_key")
    }, tags={ "inventor", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Inventor.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "the inventor has not been found", response = Void.class) })
    public Response getInventorById(@ApiParam(value = "the ID of the inventor that need to be returned",required=true) @PathParam("inventorId") Long inventorId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getInventorById(inventorId,securityContext);
    }
    @GET
    @Path("/findByDate")
    
    
    @io.swagger.annotations.ApiOperation(value = "find inventors by date", notes = "", response = Inventor.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "event_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:event", description = "read the inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:event", description = "modify inventors in the dataBase")
        })
    }, tags={ "inventor", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Inventor.class, responseContainer = "List") })
    public Response inventorfindByDate(@ApiParam(value = "") @QueryParam("date") String date
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.inventorfindByDate(date,securityContext);
    }
    @PUT
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update an existing inventor", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "inventor_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:inventor", description = "modify inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:inventor", description = "read the inventors in the dataBase")
        })
    }, tags={ "inventor", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Validation exception", response = Void.class) })
    public Response updateInventor(@ApiParam(value = "The inventor that needs to be added in the dataBase" ,required=true) Inventor inventor
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateInventor(inventor,securityContext);
    }
    @POST
    @Path("/{inventorId}")
    @Consumes({ "application/x-www-form-urlencoded" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updates an inventor in the dataBase with form data", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "inventor_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:inventor", description = "modify inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:inventor", description = "read the inventors in the dataBase")
        })
    }, tags={ "inventor", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input", response = Void.class) })
    public Response updateInventorWithForm(@ApiParam(value = "the ID of the inventor that needs to be updated",required=true) @PathParam("inventorId") Long inventorId
,@ApiParam(value = "Updated name of the inventor")  @FormParam("name")  String name
,@ApiParam(value = "Updated status of the inventor")  @FormParam("status")  String status
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateInventorWithForm(inventorId,name,status,securityContext);
    }
    @POST
    @Path("/{inventorId}/uploadImage")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "uploads an image", notes = "", response = ModelApiResponse.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "inventor_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:inventor", description = "modify inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:inventor", description = "read the inventors in the dataBase")
        })
    }, tags={ "inventor", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ModelApiResponse.class) })
    public Response uploadImage(@ApiParam(value = "the ID of the inventor that needs to be updated",required=true) @PathParam("inventorId") Long inventorId
,@ApiParam(value = "Additional data to pass to server")@FormDataParam("additionalMetadata")  String additionalMetadata
,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.uploadImage(inventorId,additionalMetadata,fileInputStream, fileDetail,securityContext);
    }
}
