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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")
public class UserApiServiceImpl extends UserApiService {
    @Override
    public Response adduser(User body, SecurityContext securityContext) throws NotFoundException {
		String query = "INSERT INTO User(Username, UserId, FirstName, LastName, Email, Phone, Password) VALUES(?,?,?,?,?,?;?)";
		    	
		    	Connection conn = ConnectionManager.getConnection();
		    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			        pstmt.setString(1, body.getUsername());
			        pstmt.setLong(2, body.getId());
			        pstmt.setString(3, body.getFirstName());
			        pstmt.setString(4, body.getLastName());
			        pstmt.setString(5, body.getEmail());
			        pstmt.setString(6, body.getPhone());
			        pstmt.setString(7, body.getPassword());
			        pstmt.executeUpdate(); 
			        pstmt.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
		        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
		    }
    @Override
    public Response deleteUser(String username, SecurityContext securityContext) throws NotFoundException {
       	String query = "DELETE FROM User WHERE UserId = ?";
    	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
		    pstmt.setString(1, null);
		    pstmt.executeUpdate();
	    	} catch (SQLException e) {
		    e.printStackTrace();
	    	}
	        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "user deleted!")).build();
	    }

    @Override
    public Response getUserByName(String username, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Username, UserId, FirstName, LastName, Email, Phone, Password FROM User WHERE Username = ?"; 
		User usr = new User();
		Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    		preparedStmt.setString(1, username);
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
		        e.printStackTrace();
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
       	String query = "UPDATE Actor SET Username = ?, FirstName = ?, LastName = ?, Email = ?, Phone = ?, Password = ? where UserId = ?"; 
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
		        e.printStackTrace();
			}
	    	return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "User updated!")).build();
		}

}
