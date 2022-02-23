package io.swagger.api.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import io.swagger.api.*;
import io.swagger.model.*;

import java.io.File;
import io.swagger.model.Inventor;
import io.swagger.model.ModelApiResponse;
import io.swagger.util.Json;

import java.util.List;
import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-21T11:04:43.972Z")
public class InventorApiServiceImpl extends InventorApiService {
    @Override
    public Response addInventor(Inventor inventor, SecurityContext securityContext) throws NotFoundException {
    	String query = "INSERT INTO Actor(NomActor, DateNaissance, DateMort, Nationalite, PrenomActor, Status) VALUES(?,?,?,?,?,?)";
    	try (Connection conn = ConnectionManager.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, inventor.getName());
	        pstmt.setString(2, inventor.getBorndate());
	        pstmt.setString(3, inventor.getDeathdate());
	        pstmt.setString(4, inventor.getNationalite());
	        pstmt.setString(5, inventor.getFirstname());
	        pstmt.setString(6, inventor.getStatus());
	        pstmt.executeUpdate();
	        pstmt.close();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
    	return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "New inventor added")).build();
    }

    @Override
    public Response deleteInventor(Long inventorId, String apiKey, SecurityContext securityContext) throws NotFoundException {
        String query = "DELETE FROM Actor where Entity id = ?";

        try (Connection conn = ConnectionManager.getConnection();
  	            PreparedStatement pstmt = conn.prepareStatement(query)) {
  	        pstmt.setLong(1, inventorId);
  	        pstmt.executeUpdate();
  	        pstmt.close();
  	    } catch (SQLException e) {
  	        System.out.println(e.getMessage());
  	    }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor deleted!")).build();
  	}

    @Override
    public Response findInventorByInvention( @NotNull List<String> invention, SecurityContext securityContext) throws NotFoundException {
		String query = "SELECT NomActor, DateNaissance, DateMort, Entity id, Nationalite, PrenomActor, Status from Actor WHERE Entity id = ? UNION SELECT * FROM Invention WHERE Entity id = ? ORDER BY Entity id";
		Inventor inv=new Inventor();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\t\tStatus\n");
		rst.next();
    	inv.setName(rst.getString(1));
    	inv.setBorndate(rst.getString(2));
    	inv.setDeathdate(rst.getString(3));
    	inv.setId(rst.getLong(4));
    	inv.setNationalite(rst.getString(5));
    	inv.setFirstname(rst.getString(6));
    	inv.setStatus(rst.getString(7));

	   /*System.out.print(rst.getString(1));
	   System.out.print("\t\t\t\t\t"+rst.getString(2));
	   System.out.print("\t\t\t\t\t"+rst.getString(3));
	   System.out.print("\t\t\t\t\t"+rst.getLong(4));
	   System.out.print("\t\t\t\t\t"+rst.getString(5));
	   System.out.print("\t\t\t\t\t"+rst.getString(6));
	   System.out.print("\t\t\t\t\t"+rst.getString(7));
	   System.out.println();*/

		   rst.close();
		   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok(inv).build();
	}

    @Override
    public Response findInventorsByName( @NotNull String name, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomActor, DateNaissance, DateMort, Entity id, Nationalite, PrenomActor, Status FROM Actor WHERE NomActor = ? and PrenomActor = ?";
    	Inventor inv=new Inventor();

    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\t\tStatus\n");
		rst.next();
		   inv.setName("Gates");
	       inv.setBorndate(rst.getString(2));
	       inv.setDeathdate(rst.getString(3));
	       inv.setId(rst.getLong(4));
	       inv.setNationalite(rst.getString(5));
	       inv.setFirstname(rst.getString(6));
	       inv.setStatus(rst.getString(7));

		   /*System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getString(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getLong(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.print("\t\t\t\t\t"+rst.getString(6));
		   System.out.print("\t\t\t\t\t"+rst.getString(7));
		   System.out.println();*/

	   rst.close();
	   preparedStmt.close();

		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
    	return Response.ok(inv).build();
    }

    @Override
    public Response findInventorsByStatus( @NotNull String status, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomActor, DateNaissance, DateMort, Entity id, Nationalite, PrenomActor, Status from Actor WHERE Status = ?";
    	Inventor inv=new Inventor();
    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\t\tStatus\n");
		rst.next();
	       inv.setName(rst.getString(1));
	       inv.setBorndate(rst.getString(2));
	       inv.setDeathdate(rst.getString(3));
	       inv.setId(rst.getLong(4));
	       inv.setNationalite(rst.getString(5));
	       inv.setFirstname(rst.getString(6));
	       inv.setStatus(rst.getString(7));

		   /*System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getString(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getLong(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.print("\t\t\t\t\t"+rst.getString(6));
		   System.out.print("\t\t\t\t\t"+rst.getString(7));
		   System.out.println();*/
		   rst.close();
		   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "inventor found!")).build();
    }
    @Override
    public Response getInventorById(Long inventorId, SecurityContext securityContext) throws NotFoundException {
       	String query = "SELECT NomActor, DateNaissance, DateMort, Entity id, Nationalite, PrenomActor, Status from Actor WHERE Entity id = ?";
       	Inventor inv=new Inventor();
       	try (Connection conn = ConnectionManager.getConnection();
    				PreparedStatement preparedStmt = conn.prepareStatement(query)){
    		ResultSet rst = preparedStmt.executeQuery();
    		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\t\tStatus\n");
    		rst.next();
	 	       inv.setName(rst.getString(1));
	 	       inv.setBorndate(rst.getString(2));
	 	       inv.setDeathdate(rst.getString(3));
	 	       inv.setId(rst.getLong(4));
	 	       inv.setNationalite(rst.getString(5));
	 	       inv.setFirstname(rst.getString(6));
	 	       inv.setStatus(rst.getString(7));
    		   /*System.out.print(rst.getString(1));
    		   System.out.print("\t\t\t\t\t"+rst.getString(2));
    		   System.out.print("\t\t\t\t\t"+rst.getString(3));
    		   System.out.print("\t\t\t\t\t"+rst.getLong(4));
    		   System.out.print("\t\t\t\t\t"+rst.getString(5));
    		   System.out.print("\t\t\t\t\t"+rst.getString(6));
    		   System.out.print("\t\t\t\t\t"+rst.getString(7));
    		   System.out.println();*/

    		   rst.close();
    		   preparedStmt.close();
    		}catch (SQLException e) {
    	        System.out.println(e.getMessage());
    		}
       		return Response.ok(inv).build();
    	}

    @Override
    public Response inventorfindByDate( String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomActor, DateNaissance, DateMort, Entity id, Nationalite, PrenomActor, Status from Actor WHERE DateNaissance = ?";
    	Inventor inv=new Inventor();
    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\t\tStatus\n");
		rst.next();
	       inv.setName(rst.getString(1));
	       inv.setBorndate(rst.getString(2));
	       inv.setDeathdate(rst.getString(3));
	       inv.setId(rst.getLong(4));
	       inv.setNationalite(rst.getString(5));
	       inv.setFirstname(rst.getString(6));
	       inv.setStatus(rst.getString(7));

		   /*System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getString(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getLong(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.print("\t\t\t\t\t"+rst.getString(6));
		   System.out.print("\t\t\t\t\t"+rst.getString(7));
		   System.out.println();*/

		   rst.close();
		   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
    	return Response.ok(inv).build();
	}

    @Override
    public Response updateInventor(Inventor inventor, SecurityContext securityContext) throws NotFoundException {
    	String query = "UPDATE Actor SET NomActor = ?, DateNaissance = ?, DateMort = ?, Nationalite = ?, PrenomActor = ?, Status = ? where Entity id = ?";
    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
	    preparedStmt.setString(1, inventor.getName());
	    preparedStmt.setString(2, inventor.getBorndate());
	    preparedStmt.setString(3, inventor.getDeathdate());
	    preparedStmt.setString(4, inventor.getNationalite());
	    preparedStmt.setString(5, inventor.getFirstname());
	    preparedStmt.setString(6, inventor.getStatus());
	    preparedStmt.executeUpdate();
	    preparedStmt.close();
    	}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
    	return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor found!")).build();
	}

    @Override
    public Response updateInventorWithForm(Long inventorId, String name, String status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response uploadImage(Long inventorId, String additionalMetadata, InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
