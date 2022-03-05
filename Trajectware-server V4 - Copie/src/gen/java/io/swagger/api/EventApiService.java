package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Event;
import io.swagger.model.Inventor;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")
public abstract class EventApiService {
    public abstract Response addevent(Event events,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteEvent(Long eventId,String apiKey,SecurityContext securityContext) throws NotFoundException;
    public abstract Response eventfindByDate( String date,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findEventsByInvention( @NotNull List<String> event,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findEventsByInventor( @NotNull List<String> event,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findEventsByName( @NotNull String name,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getEventById(Long eventId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateEventWithForm(Long eventId,String name,String status,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateevent(Event events,SecurityContext securityContext) throws NotFoundException;
}
