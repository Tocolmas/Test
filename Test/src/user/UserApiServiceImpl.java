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
import io.swagger.api.NotFoundException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2021-12-23T10:17:36.264Z")
public class UserApiServiceImpl extends UserApiService { // ?il manque l'ajout des utilisateurs, sauf si seul utilisateur musée ?
    @Override
    private Connection connect() {
	    String url = "jdbc:sqlite:c://sqlite/db/projet.db";
	    Connection conn = null;
	    try {
	        conn = DriverManager.getConnection(url);
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return conn;
	}
    
    public Response deleteUser(String username, SecurityContext securityContext) throws NotFoundException {
    	String query = "delete from User where Userid = ?";
	  
    	try (Connection conn = this.connect();
    		PreparedStatement pstmt = conn.prepareStatement(query)) {
	    pstmt.setInt(1, 1);
	    pstmt.executeUpdate();
    	} catch (SQLException e) {
	    System.out.println(e.getMessage());
    	}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "user deleted!")).build();
    }
    @Override
    public Response getUserByName(String username, SecurityContext securityContext) throws NotFoundException {
    	String query = "select Username from User"; /*Pas sur de pouvoir faire ça*/
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tUsername\t\tUserid\t\tDateCreation\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t"+rst.getInt(2));
		   System.out.print("\t\t"+rst.getSring(3));
		   System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "user found!")).build();
    }
    @Override
    public Response loginUser( @NotNull String username,  @NotNull String password, SecurityContext securityContext) throws NotFoundException {
	    //Checks to see if Login is Administrator and then sends him to his respective location
	    /*String username = Username.getText();
	    String password = Password.getText();
	    
	    try (Connection conn = this.connect();{
	        String query1 = "select * from User where Username='"+username+"' and Password='"+password+"' and Position=?";
	        PreparedStatement prepared_stmt = conn.prepareStatement(query1);
	        ResultSet rs1 = prepared_stmt.executeQuery();
	        if (rs1.next()){
	            String add1 = rs1.getString("Username");
	            usernameCon1.setText(add1);
	            String add2 = rs1.getString("Password");
	            passwordCon1.setText(add2);
	            String add3 = rs1.getString("Position");
	            positionCon1.setText(add3);
	        }
	        pst1.close();
	        rs1.close();
	    }catch(Exception e){
	        JOptionPane.showMessageDialog(null, e);
	    }
	}
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
}*/
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
