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
import java.util.List;
import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2021-12-23T10:17:36.264Z")
public class InventionApiServiceImpl extends InventionApiService {
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
	public void insert(String NomInvention, int Finie, String Commencement, String Fin) {
	    String query = "INSERT INTO Invention(NomInvention, Finie, Commencement, Fin) VALUES(?,?,?,?)";
	
	    try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, NomInvention);
	        pstmt.setInt(2, Finie);
	        pstmt.setString(3, Commencement);
	        pstmt.setString(4, Fin);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public Response addInvention(Invention invention, SecurityContext securityContext) throws NotFoundException {
	    insert inv = new insert();
	    // insert three new rows
	    inv.insert("Mac", "0");
	    inv.insert("Souris", "0");
	    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Invention added")).build();
}

    @Override
    public Response deleteInvention(Long inventionId, String apiKey, SecurityContext securityContext) throws NotFoundException {
    	String query = "delete from Invention where Entity id = ?";
  
    	try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, "Atom");
        pstmt.executeUpdate();
    	} catch (SQLException e) {
        System.out.println(e.getMessage());
    	}
    	return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Invention deleted!")).build();
    }

    @Override
    public Response findByDate( String date, SecurityContext securityContext) throws NotFoundException {
String query = "select Commancement, Fin from Invention"; /*Pas sur de pouvoir daire ça*/
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomInvention\t\tEntity id\t\tFinie\t\tCommencement\t\tFin\n");
		while(rst.next()) {
			System.out.print(rst.getString(1));
			System.out.print("\t\t\t\t"+rst.getInt(2));
		    System.out.print("\t\t\t\t"+rst.getInt(3));
		    System.out.print("\t\t\t\t"+rst.getString(4));
		    System.out.print("\t\t\t\t"+rst.getString(5));
		    System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response findInventionByInventor( @NotNull List<String> inventor, SecurityContext securityContext) throws NotFoundException {
    	String query = "select Entity id from Actor, Invention WHERE Entity id.Actor = Entity id.Invention "; /*Pas sur de pouvoir daire ça*/
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomInvention\t\tEntity id\t\tFinie\t\tCommencement\t\tFin\n");
		while(rst.next()) {
			System.out.print(rst.getString(1));
			System.out.print("\t\t\t\t"+rst.getInt(2));
		    System.out.print("\t\t\t\t"+rst.getInt(3));
		    System.out.print("\t\t\t\t"+rst.getString(4));
		    System.out.print("\t\t\t\t"+rst.getString(5));
		    System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "invention found!")).build();
    }
    @Override
    public Response findInventionBysByTags( @NotNull List<String> tags,  String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "select Entity id from Invention, Feature WHERE Entity id.Invention = Entity id.Feature"; /*Pas sur de pouvoir daire ça*/
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomInvention\t\tEntity id\t\tFinie\t\tCommencement\t\tFin\n");
		while(rst.next()) {
			System.out.print(rst.getString(1));
			System.out.print("\t\t\t\t"+rst.getInt(2));
		    System.out.print("\t\t\t\t"+rst.getInt(3));
		    System.out.print("\t\t\t\t"+rst.getString(4));
		    System.out.print("\t\t\t\t"+rst.getString(5));
		    System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "invention found!")).build();
    }
    @Override
    public Response findInventionsByStatus( @NotNull List<String> status, SecurityContext securityContext) throws NotFoundException {
    	String query = "select NomInvention from Invention, Status WHERE NomInvention.Invention = NomInvention.Status "; /*Pas sur de pouvoir faire ça*/
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomInvention\t\tEntity id\t\tFinie\t\tCommencement\t\tFin\n");
		while(rst.next()) {
			System.out.print(rst.getString(1));
			System.out.print("\t\t\t\t"+rst.getInt(2));
		    System.out.print("\t\t\t\t"+rst.getInt(3));
		    System.out.print("\t\t\t\t"+rst.getString(4));
		    System.out.print("\t\t\t\t"+rst.getString(5));
		    System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventio, found!")).build();
	}

    @Override
    public Response getInventionById(Long inventionId, SecurityContext securityContext) throws NotFoundException {
    	String query = "select Entity id from Invention"; /*Pas sur de pouvoir daire ça*/
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomInvention\t\tEntity id\t\tFinie\t\tCommencement\t\tFin\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t"+rst.getInt(2));
		   System.out.print("\t\t\t\t"+rst.getInt(3));
		   System.out.print("\t\t\t\t"+rst.getString(4));
		   System.out.print("\t\t\t\t"+rst.getString(5));
		   System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "invention found!")).build();
    }
    @Override
    public Response updateInvention(Invention body, SecurityContext securityContext) throws NotFoundException {
    	String query = "update Invention set Fin = ? where NomInvention = ?"; 
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
	    preparedStmt.setInt   (1, 2022);
	    preparedStmt.setString(2, "Joe");
	    preparedStmt.executeUpdate();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updateInventionWithForm(Long inventionId, String name, String status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response uploadFile(Long inventionId, String additionalMetadata, InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
        // do some magic! Voir inventor
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
