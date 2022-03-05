package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.InventionApiService;
import io.swagger.api.factories.InventionApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import java.io.File;
import io.swagger.model.Invention;
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

@Path("/invention")


@io.swagger.annotations.Api(description = "the invention API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")
public class InventionApi  {
   private final InventionApiService delegate;

   public InventionApi(@Context ServletConfig servletContext) {
      InventionApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("InventionApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (InventionApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = InventionApiServiceFactory.getInventionApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add a new invention in the dataBase", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "invention_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:invention", description = "read the inventions"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:invention", description = "modify inventions in the dataBase")
        })
    }, tags={ "invention", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Operation successful", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "The invention you are trying to add already exists", response = Void.class) })
    public Response addInvention(@ApiParam(value = "The invention that needs to be added in the dataBase" ,required=true) Invention body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addInvention(body,securityContext);
    }
    @DELETE
    @Path("/{inventionId}")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Deletes an invention", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "invention_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:invention", description = "read the inventions"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:invention", description = "modify inventions in the dataBase")
        })
    }, tags={ "invention", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class) })
    public Response deleteInvention(@ApiParam(value = "Invention id that needs to be deleted",required=true) @PathParam("inventionId") Long inventionId
,@ApiParam(value = "" )@HeaderParam("api_key") String apiKey
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteInvention(inventionId,apiKey,securityContext);
    }
    @GET
    @Path("/findByDate")
    
    
    @io.swagger.annotations.ApiOperation(value = "find invention by date", notes = "", response = Invention.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "event_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:event", description = "read the inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:event", description = "modify inventors in the dataBase")
        })
    }, tags={ "invention", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Invention.class, responseContainer = "List") })
    public Response findByDate(@ApiParam(value = "") @QueryParam("date") String date
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findByDate(date,securityContext);
    }
    @GET
    @Path("/findByInventor")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds inventions by their inventors", notes = "Allows to find inventions by their inventors", response = Invention.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "invention_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:invention", description = "read the inventions"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:invention", description = "modify inventions in the dataBase")
        })
    }, tags={ "invention", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Invention.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid value", response = Void.class) })
    public Response findInventionByInventor(@ApiParam(value = "Inventors to filter the inventions by",required=true) @QueryParam("inventor") List<String> inventor
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findInventionByInventor(inventor,securityContext);
    }
    @GET
    @Path("/findByTags")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds inventions by tags", notes = "", response = Invention.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "invention_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:invention", description = "read the inventions"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:invention", description = "modify inventions in the dataBase")
        })
    }, tags={ "invention", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Invention.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid tag value", response = Void.class) })
    public Response findInventionBysByTags(@ApiParam(value = "Tags to filter by",required=true, allowableValues="CPU, IHM, Micro, OS, APP, ...") @QueryParam("tags") List<String> tags
,@ApiParam(value = "the dates of any particular invention") @QueryParam("date") String date
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findInventionBysByTags(tags,date,securityContext);
    }
    @GET
    @Path("/findByName")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds inventors by Name", notes = "the name of the inventor", response = Invention.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "invention_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:invention", description = "read the inventions"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:invention", description = "modify inventions in the dataBase")
        })
    }, tags={ "inventor", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Invention.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid status value", response = Void.class) })
    public Response findInventionsByName(@ApiParam(value = "",required=true) @QueryParam("name") String name
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findInventionsByName(name,securityContext);
    }
    @GET
    @Path("/findByStatus")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds inventions by status", notes = "the status of the invention (finished or unfinished)", response = Invention.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "invention_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:invention", description = "read the inventions"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:invention", description = "modify inventions in the dataBase")
        })
    }, tags={ "invention", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Invention.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid status value", response = Void.class) })
    public Response findInventionsByStatus(@ApiParam(value = "the status of the invention (finished or unfinished)",required=true) @QueryParam("status") String status
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findInventionsByStatus(status,securityContext);
    }
    @GET
    @Path("/{inventionId}")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Find an invention by its ID", notes = "Returns only one invention", response = Invention.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "api_key")
    }, tags={ "invention", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Invention.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "the invention has not been found", response = Void.class) })
    public Response getInventionById(@ApiParam(value = "the ID of the invention that need to be returned",required=true) @PathParam("inventionId") Long inventionId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getInventionById(inventionId,securityContext);
    }
    @PUT
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update an existing invention", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "invention_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:invention", description = "read the inventions"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:invention", description = "modify inventions in the dataBase")
        })
    }, tags={ "invention", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Validation exception", response = Void.class) })
    public Response updateInvention(@ApiParam(value = "The invention that needs to be added in the dataBase" ,required=true) Invention body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateInvention(body,securityContext);
    }
    @POST
    @Path("/{inventionId}")
    @Consumes({ "application/x-www-form-urlencoded" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updates an invention in the dataBase with form data", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "invention_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:invention", description = "read the inventions"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:invention", description = "modify inventions in the dataBase")
        })
    }, tags={ "invention", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input", response = Void.class) })
    public Response updateInventionWithForm(@ApiParam(value = "the ID of the invention that needs to be updated",required=true) @PathParam("inventionId") Long inventionId
,@ApiParam(value = "Updated name of the invention")  @FormParam("name")  String name
,@ApiParam(value = "Updated status of the invention")  @FormParam("status")  String status
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateInventionWithForm(inventionId,name,status,securityContext);
    }
    @POST
    @Path("/{inventionId}/uploadImage")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "uploads an image", notes = "", response = ModelApiResponse.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "invention_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:invention", description = "read the inventions"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:invention", description = "modify inventions in the dataBase")
        })
    }, tags={ "invention", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ModelApiResponse.class) })
    public Response uploadFile(@ApiParam(value = "the ID of the invention that needs to be updated",required=true) @PathParam("inventionId") Long inventionId
,@ApiParam(value = "Additional data to pass to server")@FormDataParam("additionalMetadata")  String additionalMetadata
,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.uploadFile(inventionId,additionalMetadata,fileInputStream, fileDetail,securityContext);
    }
}
