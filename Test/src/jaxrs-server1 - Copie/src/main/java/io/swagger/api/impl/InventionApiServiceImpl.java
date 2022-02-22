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
import io.swagger.model.Invention;
import io.swagger.model.ModelApiResponse;

import java.util.List;
import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-21T11:04:43.972Z")
public class InventionApiServiceImpl extends InventionApiService {
    @Override
    public Response addInvention(Invention body, SecurityContext securityContext) throws NotFoundException {
    	 String query = "INSERT INTO Invention(NomInvention, Status, Commencement, Fin) VALUES(?,?,?,?)";

 	    try (Connection conn = ConnectionManager.getConnection();
 	            PreparedStatement pstmt = conn.prepareStatement(query)) {
 	        pstmt.setString(1, body.getName());
 	        pstmt.setString(2, body.getStatus());
 	        pstmt.setString(3, body.getStartdate());
 	        pstmt.setString(4, body.getFinsihdate());
 	        pstmt.executeUpdate();
 	        pstmt.close();
 	    } catch (SQLException e) {
 	        System.out.println(e.getMessage());
 	    }
         return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "new invention added!")).build();
     }

    @Override
    public Response deleteInvention(Long inventionId, String apiKey, SecurityContext securityContext) throws NotFoundException {
    	String query = "DELETE FROM Invention where Entity id = ?";

    	try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setLong(1, inventionId);
        pstmt.executeUpdate();
        pstmt.close();
    	} catch (SQLException e) {
        System.out.println(e.getMessage());
    	}
    	return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Invention deleted!")).build();
    }

    @Override
    public Response findByDate( String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomInvention, Entity id, Status, Commencement, Fin FROM Invention WHERE Commencement = ?";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomInvention\t\tEntity id\t\tStatus\t\tCommencement\t\tFin\n");
		while(rst.next()) {
			System.out.print(rst.getString(1));
			System.out.print("\t\t\t\t"+rst.getLong(2));
			System.out.print("\t\t\t\t"+rst.getInt(3));
		    System.out.print("\t\t\t\t"+rst.getString(4));
		    System.out.print("\t\t\t\t"+rst.getString(5));
		    System.out.print("\t\t\t\t"+rst.getString(6));
		    System.out.println();
		}
		    rst.close();
		    preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "invention found!")).build();
    }

    @Override
    public Response findInventionByInventor( @NotNull List<String> inventor, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomInvention, Entity id, Status, Commencement, Fin FROM Invention WHERE Entity id = ? UNION SELECT * FROM INVENTOR WHERE Entity id = ?";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomInvention\t\tEntity id\t\tStatus\t\tCommencement\t\tFin\n");
		while(rst.next()) {
			System.out.print(rst.getString(1));
			System.out.print("\t\t\t\t"+rst.getLong(2));
		    System.out.print("\t\t\t\t"+rst.getString(3));
		    System.out.print("\t\t\t\t"+rst.getString(4));
		    System.out.print("\t\t\t\t"+rst.getString(5));
		    System.out.println();
		}
		    rst.close();
		    preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "invention found!")).build();
    }

    @Override
    public Response findInventionBysByTags( @NotNull List<String> tags,  String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomInvention, Entity id, Status, Commencement, Fin FROM Invention WHERE Entity id = ?";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomInvention\t\tEntity id\t\tStatus\t\tCommencement\t\tFin\n");
		while(rst.next()) {
			System.out.print(rst.getString(1));
			System.out.print("\t\t\t\t"+rst.getLong(2));
		    System.out.print("\t\t\t\t"+rst.getString(3));
		    System.out.print("\t\t\t\t"+rst.getString(4));
		    System.out.print("\t\t\t\t"+rst.getString(5));
		    System.out.println();
		}
		    rst.close();
		    preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "invention found!")).build();
    }

    @Override
    public Response findInventionsByName( @NotNull String name, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomInvention, Entity id, Status, Commencement, Fin from Invention WHERE NomInvention = ?";

    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomInvention\t\tEntity id\t\tStatus\t\tCommencement\t\tFin\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getLong(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getString(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.println();
		}
		   rst.close();
		   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "invention found!")).build();
    }
    @Override
    public Response findInventionsByStatus( @NotNull String status, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomInvention, Entity id, Status, Commencement, Fin FROM Invention WHERE Status = ?";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomInvention\t\tEntity id\t\tStatus\t\tCommencement\t\tFin\n");
		while(rst.next()) {
			System.out.print(rst.getString(1));
			System.out.print("\t\t\t\t"+rst.getInt(2));
		    System.out.print("\t\t\t\t"+rst.getString(3));
		    System.out.print("\t\t\t\t"+rst.getString(4));
		    System.out.print("\t\t\t\t"+rst.getString(5));
		    System.out.println();
		}
		    rst.close();
		    preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Invention, found!")).build();
	}

    @Override
    public Response getInventionById(Long inventionId, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomInvention, Entity id, Status, Commencement, Fin FROM Invention WHERE Entity id = ?";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomInvention\t\tEntity id\t\tStatus\t\tCommencement\t\tFin\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t"+rst.getInt(2));
		   System.out.print("\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t"+rst.getString(4));
		   System.out.print("\t\t\t\t"+rst.getString(5));
		   System.out.println();
		}
		   rst.close();
		   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "invention found!")).build();
    }

    @Override
    public Response updateInvention(Invention body, SecurityContext securityContext) throws NotFoundException {
       	String query = "UPDATE Invention SET NomInvention = ?, Status = ?, Commencement = ?, Fin = ? where NomInvention = ? AND Entity id = ?";

    		try (Connection conn = ConnectionManager.getConnection();
    				PreparedStatement preparedStmt = conn.prepareStatement(query)){
    	    preparedStmt.setString(1, body.getName());
    	    preparedStmt.setString(2, body.getStatus());
    	    preparedStmt.setString(2, body.getStartdate());
    	    preparedStmt.setString(2, body.getFinsihdate());
    	    preparedStmt.executeUpdate();
    	    preparedStmt.close();
    		}catch (SQLException e) {
    	        System.out.println(e.getMessage());
    		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updateInventionWithForm(Long inventionId, String name, String status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    public Response uploadFile(Long inventionId, String additionalMetadata, InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
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
