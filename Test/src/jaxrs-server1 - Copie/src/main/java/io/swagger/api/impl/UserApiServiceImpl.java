package io.swagger.api.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.User;

import java.util.List;
import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-19T09:33:27.588Z")
public class UserApiServiceImpl extends UserApiService {
    @Override
    public Response deleteUser(String username, SecurityContext securityContext) throws NotFoundException {
    	String query = "DELETE FROM User WHERE Userid = ?";
  	  
    	try (Connection conn = ConnectionManager.getConnection();
    		PreparedStatement pstmt = conn.prepareStatement(query)) {
	    pstmt.setString(1, null);
	    pstmt.executeUpdate();
    	} catch (SQLException e) {
	    System.out.println(e.getMessage());
    	}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "user deleted!")).build();
    }

    @Override
    public Response getUserByName(String username, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Username, UserId, DateCreation FROM User WHERE Username = ?"; 
		
    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tUsername\t\tUserid\t\tDateCreation\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t"+rst.getLong(2));
		   System.out.print("\t\t"+rst.getString(3));
		   System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response loginUser( @NotNull String username,  @NotNull String password, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response logoutUser(SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updateUser(String username, User body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
