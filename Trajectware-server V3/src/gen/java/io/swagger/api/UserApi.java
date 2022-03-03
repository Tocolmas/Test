package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.UserApiService;
import io.swagger.api.factories.UserApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.User;

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

@Path("/user")


@io.swagger.annotations.Api(description = "the user API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")
public class UserApi  {
   private final UserApiService delegate;

   public UserApi(@Context ServletConfig servletContext) {
      UserApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("UserApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (UserApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = UserApiServiceFactory.getUserApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add a new user in the dataBase", notes = "", response = Void.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Operation successful", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "The invention you are trying to add already exists", response = Void.class) })
    public Response adduser(@ApiParam(value = "The user that needs to be added in the dataBase" ,required=true) User body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.adduser(body,securityContext);
    }
    @DELETE
    @Path("/{username}")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete a user", notes = "It can only be done by the user who is logged in", response = Void.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid username supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access Forbidden", response = Void.class) })
    public Response deleteUser(@ApiParam(value = "The name of the user that needs to be deleted",required=true) @PathParam("username") String username
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteUser(username,securityContext);
    }
    @GET
    @Path("/{username}")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get user by their username", notes = "", response = User.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "the user was not found", response = Void.class) })
    public Response getUserByName(@ApiParam(value = "The name that must be retrieved",required=true) @PathParam("username") String username
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getUserByName(username,securityContext);
    }
    @GET
    @Path("/login")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Logs the user into the system", notes = "", response = String.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = String.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid username/password supplied", response = Void.class) })
    public Response loginUser(@ApiParam(value = "The name of the user for login",required=true) @QueryParam("username") String username
,@ApiParam(value = "The password of the user for login in clear text",required=true) @QueryParam("password") String password
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.loginUser(username,password,securityContext);
    }
    @GET
    @Path("/logout")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Logs out the user that is currently logged in a user session", notes = "", response = Void.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class) })
    public Response logoutUser(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.logoutUser(securityContext);
    }
    @PUT
    @Path("/{username}")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update the user", notes = "It can only be done by the user who is logged in.", response = Void.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 404, message = "the user was not found", response = Void.class) })
    public Response updateUser(@ApiParam(value = "the name of the user that need to be updated",required=true) @PathParam("username") String username
,@ApiParam(value = "Updated a user object" ,required=true) User body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateUser(username,body,securityContext);
    }
}
