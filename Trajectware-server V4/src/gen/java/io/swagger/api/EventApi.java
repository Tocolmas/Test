package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.EventApiService;
import io.swagger.api.factories.EventApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Event;
import io.swagger.model.Inventor;

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

@Path("/event")


@io.swagger.annotations.Api(description = "the event API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")
public class EventApi  {
   private final EventApiService delegate;

   public EventApi(@Context ServletConfig servletContext) {
      EventApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("EventApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (EventApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = EventApiServiceFactory.getEventApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add a new event in the dataBase", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "event_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:event", description = "read the inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:event", description = "modify inventors in the dataBase")
        })
    }, tags={ "event", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Acess forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "The event you are trying to add already exists", response = Void.class) })
    public Response addevent(@ApiParam(value = "The event that needs to be added in the dataBase" ,required=true) Event events
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addevent(events,securityContext);
    }
    @DELETE
    @Path("/{eventId}")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Deletes an event", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "event_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:event", description = "read the inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:event", description = "modify inventors in the dataBase")
        })
    }, tags={ "event", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbiden", response = Void.class) })
    public Response deleteEvent(@ApiParam(value = "Event id that needs to be deleted",required=true) @PathParam("eventId") Long eventId
,@ApiParam(value = "" )@HeaderParam("api_key") String apiKey
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteEvent(eventId,apiKey,securityContext);
    }
    @GET
    @Path("/findByDate")
    
    
    @io.swagger.annotations.ApiOperation(value = "find an event by date", notes = "", response = Object.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "event_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:event", description = "read the inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:event", description = "modify inventors in the dataBase")
        })
    }, tags={ "event", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "", response = Object.class) })
    public Response eventfindByDate(@ApiParam(value = "") @QueryParam("date") String date
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.eventfindByDate(date,securityContext);
    }
    @GET
    @Path("/findByInvention")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds events by their inventions", notes = "Allows to find events by their inventions", response = Event.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "event_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:event", description = "read the inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:event", description = "modify inventors in the dataBase")
        })
    }, tags={ "event", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Event.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid tag value", response = Void.class) })
    public Response findEventsByInvention(@ApiParam(value = "Descriptions to filter by",required=true) @QueryParam("event") List<String> event
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findEventsByInvention(event,securityContext);
    }
    @GET
    @Path("/findByInventor")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds events by their inventor", notes = "Allows to find events by their inventor", response = Event.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "event_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:event", description = "read the inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:event", description = "modify inventors in the dataBase")
        })
    }, tags={ "event", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Event.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid tag value", response = Void.class) })
    public Response findEventsByInventor(@ApiParam(value = "Descriptions to filter by",required=true) @QueryParam("event") List<String> event
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findEventsByInventor(event,securityContext);
    }
    @GET
    @Path("/findByName")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds events by Name", notes = "the name of the event", response = Inventor.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "event_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:event", description = "read the inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:event", description = "modify inventors in the dataBase")
        })
    }, tags={ "inventor", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Inventor.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid status value", response = Void.class) })
    public Response findEventsByName(@ApiParam(value = "",required=true) @QueryParam("name") String name
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findEventsByName(name,securityContext);
    }
    @GET
    @Path("/{eventId}")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Find an inventor by its ID", notes = "Returns only one inventor", response = Event.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "api_key")
    }, tags={ "event", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Event.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class) })
    public Response getEventById(@ApiParam(value = "the ID of the event that need to be returned",required=true) @PathParam("eventId") Long eventId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getEventById(eventId,securityContext);
    }
    @POST
    @Path("/{eventId}")
    @Consumes({ "application/x-www-form-urlencoded" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updates an event in the dataBase with form data", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "event_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:event", description = "read the inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:event", description = "modify inventors in the dataBase")
        })
    }, tags={ "event", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input", response = Void.class) })
    public Response updateEventWithForm(@ApiParam(value = "the ID of the event that needs to be updated",required=true) @PathParam("eventId") Long eventId
,@ApiParam(value = "Updated name of the event")  @FormParam("name")  String name
,@ApiParam(value = "Updated status of the event")  @FormParam("status")  String status
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateEventWithForm(eventId,name,status,securityContext);
    }
    @PUT
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update an existing event", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "event_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:event", description = "read the inventors in the dataBase"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:event", description = "modify inventors in the dataBase")
        })
    }, tags={ "event", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Validation exception", response = Void.class) })
    public Response updateevent(@ApiParam(value = "The event that needs to be added in the dataBase" ,required=true) Event events
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateevent(events,securityContext);
    }
}
