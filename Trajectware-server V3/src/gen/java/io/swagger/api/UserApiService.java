package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.User;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")
public abstract class UserApiService {
    public abstract Response adduser(User body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteUser(String username,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getUserByName(String username,SecurityContext securityContext) throws NotFoundException;
    public abstract Response loginUser( @NotNull String username, @NotNull String password,SecurityContext securityContext) throws NotFoundException;
    public abstract Response logoutUser(SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateUser(String username,User body,SecurityContext securityContext) throws NotFoundException;
}
