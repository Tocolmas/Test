package io.swagger.api.impl;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;


import io.swagger.api.*;
import io.swagger.model.*;


import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-21T11:04:43.972Z")
public class ImageApiServiceImpl extends ImageApiService {
    @Override
    public Response getImageById(Long fileId, SecurityContext securityContext) throws NotFoundException {
      String query = "SELECT * FROM Photo WHERE PhotoId = ?";
      File fi = new File();
      Connection conn = ConnectionManager.getConnection();
      try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    	  preparedStmt.setLong(1, fileId);
	      ResultSet rst = preparedStmt.executeQuery();
	      System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\t\tStatus\n");
	      rst.next();
	      fi.setId(rst.getString(1));
	      System.out.print(rst.getLong(1));
	      System.out.print("\t\t\t"+rst.getString(2));
	      System.out.print("\t\t\t"+rst.getBlob(3));
	      System.out.println();
	  
	      //rst.close();
	      //preparedStmt.close();
	      }catch (SQLException e) {
	            System.out.println(e.getMessage());
	      }
	      return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor found!")).build();
	    }
	}
	
