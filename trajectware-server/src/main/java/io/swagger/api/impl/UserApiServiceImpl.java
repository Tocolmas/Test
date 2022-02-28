package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.User;

import java.util.List;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-21T11:04:43.972Z")
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
    	String query = "SELECT Username, UserId, FirstName, LastName, Email, Phone, Password FROM User WHERE Username = ?";
		User usr = new User();
    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tUsername\t\tUserid\t\tDateCreation\n");
		rst.next();
		usr.setUsername(rst.getString(1));
		usr.setId(rst.getLong(2));
		usr.setFirstName(rst.getString(3));
		usr.setLastName(rst.getString(4));
		usr.setEmail(rst.getString(5));
		usr.setPhone(rst.getString(6));
		usr.setPassword(rst.getString(7));

	   System.out.print("\t\t"+rst.getString(1));
	   System.out.print("\t\t"+rst.getLong(2));
	   System.out.print("\t\t"+rst.getString(3));
	   System.out.print("\t\t"+rst.getString(4));
	   System.out.print("\t\t"+rst.getString(5));
	   System.out.print("\t\t"+rst.getString(6));
	   System.out.print("\t\t"+rst.getString(7));
	   System.out.println();
		}catch (SQLException e) {
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
    	String query = "UPDATE Actor SET Username = ?, FirstName = ?, LastName = ?, Email = ?, Phone = ?, Password = ? where Userid = ?";
    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
	    preparedStmt.setString(1, body.getUsername());
	    preparedStmt.setString(2, body.getFirstName());
	    preparedStmt.setString(3, body.getLastName());
	    preparedStmt.setString(4, body.getEmail());
	    preparedStmt.setString(5, body.getPhone());
	    preparedStmt.setString(6, body.getPassword());
	    preparedStmt.executeUpdate();
	    preparedStmt.close();
    	}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
    	return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "User updated!")).build();
	}
}
