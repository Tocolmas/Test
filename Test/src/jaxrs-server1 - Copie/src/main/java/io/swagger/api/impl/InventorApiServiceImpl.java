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

import java.util.List;
import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

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
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\t\tStatus\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getString(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getLong(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.print("\t\t\t\t\t"+rst.getString(6));
		   System.out.print("\t\t\t\t\t"+rst.getString(7));
		   System.out.println();
		}
		   rst.close();
		   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor found!")).build();
	}

    @Override
    public Response findInventorsByName( @NotNull String name, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomActor, DateNaissance, DateMort, Entity id, Nationalite, PrenomActor, Status from Actor WHERE NomActor = ? and PrenomActor = ?";

    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\t\tStatus\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getString(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getLong(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.print("\t\t\t\t\t"+rst.getString(6));
		   System.out.print("\t\t\t\t\t"+rst.getString(7));
		   System.out.println();
		}
		   rst.close();
		   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "inventor found!")).build();
    }
    @Override
    public Response findInventorsByStatus( @NotNull String status, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomActor, DateNaissance, DateMort, Entity id, Nationalite, PrenomActor, Status from Actor WHERE Status = ?";
    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\t\tStatus\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getString(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getLong(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.print("\t\t\t\t\t"+rst.getString(6));
		   System.out.print("\t\t\t\t\t"+rst.getString(7));
		   System.out.println();
		}
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

       	try (Connection conn = ConnectionManager.getConnection();
    				PreparedStatement preparedStmt = conn.prepareStatement(query)){
    		ResultSet rst = preparedStmt.executeQuery();
    		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\t\tStatus\n");
    		while(rst.next()) {
    		   System.out.print(rst.getString(1));
    		   System.out.print("\t\t\t\t\t"+rst.getString(2));
    		   System.out.print("\t\t\t\t\t"+rst.getString(3));
    		   System.out.print("\t\t\t\t\t"+rst.getLong(4));
    		   System.out.print("\t\t\t\t\t"+rst.getString(5));
    		   System.out.print("\t\t\t\t\t"+rst.getString(6));
    		   System.out.print("\t\t\t\t\t"+rst.getString(7));
    		   System.out.println();
    		}
    		   rst.close();
    		   preparedStmt.close();
    		}catch (SQLException e) {
    	        System.out.println(e.getMessage());
    		}
    		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor found!")).build();
    	}

    @Override
    public Response inventorfindByDate( String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomActor, DateNaissance, DateMort, Entity id, Nationalite, PrenomActor, Status from Actor WHERE DateNaissance = ?";

    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\t\tStatus\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getString(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getLong(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.print("\t\t\t\t\t"+rst.getString(6));
		   System.out.print("\t\t\t\t\t"+rst.getString(7));
		   System.out.println();
		}
		   rst.close();
		   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor found!")).build();
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
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor updated!")).build();
	}

    @Override
    public Response updateInventorWithForm(Long inventorId, String name, String status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response uploadImage(Long inventorId, String additionalMetadata, InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
       try {
         FileInputStream fis;
         int num_rows = 0;
         File image = new File("C://User/tocol/OneDrive/Bureau/aa.jpg"); //(chemin du fichier, ici c'est un exemple)
         fis = new FileInputStream (image);
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         byte[] buf = new byte[1024];
         for (int readNum; (readNum -fis.read(buf)) != -1;) {
           bos.write(buf, 0, readNum);
         }
         fis.close();
         Connection conn = ConnectionManager.getConnection();
         String query = ("INSERT INTO Photo (Photo id, NomEntity, Image) Values(?,?,?)");
         PreparedStatement preparedStmt = conn.prepareStatement(query);
         preparedStmt.setBytes(inventionId, bos.toByteArray()); // il y a un problème, je ne sais pas si on change inventionId en int au lieu de long ??

         num_rows = preparedStmt.executeUpdate();
         if (num_rows>0){
           return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
             }
             preparedStmt.close();
             conn.close();
     }catch (Exception er) {
       System.out.println(er);}
  }
}
s
