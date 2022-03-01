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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
    	 String query = "INSERT INTO Invention(Name, Status, Sartdate, Finsihdate) VALUES(?,?,?,?)";
 		
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
    	String query = "DELETE FROM Invention WHERE EntityId = ?";
  	  
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
    	String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE Startdate = ?"; 
		Invention inv = new Invention();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tName\t\tEntityId\t\tStatus\t\tStartdate\t\tFinsihdate\n");
		rst.next();
		inv.setName(rst.getString(1));
		inv.setId(rst.getLong(2));
		inv.setStatus(rst.getString(3));
		inv.setStartdate(rst.getString(4));
		inv.setFinsihdate(rst.getString(5));
		
		/*System.out.print(rst.getString(1));
		System.out.print("\t\t\t\t"+rst.getLong(2));
		System.out.print("\t\t\t\t"+rst.getInt(3));
	    System.out.print("\t\t\t\t"+rst.getString(4));
	    System.out.print("\t\t\t\t"+rst.getString(5));
	    System.out.print("\t\t\t\t"+rst.getString(6));
	    System.out.println();*/
	
	    rst.close();
	    preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok(inv).build();
}

    @Override
    public Response findInventionByInventor( @NotNull List<String> inventor, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE EntityId = ? UNION SELECT * FROM INVENTOR WHERE EntityId = ? ORDER BY EntityId.Invention";
		Invention inv = new Invention();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tName\t\tEntityId\t\tStatus\t\tStartdate\t\tFinsihdate\n");
		rst.next();
		inv.setName(rst.getString(1));
		inv.setId(rst.getLong(2));
		inv.setStatus(rst.getString(3));
		inv.setStartdate(rst.getString(4));
		inv.setFinsihdate(rst.getString(5));
		/*System.out.print(rst.getString(1));
		System.out.print("\t\t\t\t"+rst.getLong(2));
	    System.out.print("\t\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t\t"+rst.getString(4));
	    System.out.print("\t\t\t\t"+rst.getString(5));
	    System.out.println();*/
	    rst.close();
	    preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok(inv).build();
    }

    @Override
    public Response findInventionBysByTags( @NotNull List<String> tags,  String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE EntityId = ?";
		Invention inv = new Invention();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tName\t\tEntityId\t\tStatus\t\tStartdate\t\tFinsihdate\n");
		rst.next();
		inv.setName(rst.getString(1));
		inv.setId(rst.getLong(2));
		inv.setStatus(rst.getString(3));
		inv.setStartdate(rst.getString(4));
		inv.setFinsihdate(rst.getString(5));
	
		/*System.out.print(rst.getString(1));
		System.out.print("\t\t\t\t"+rst.getLong(2));
	    System.out.print("\t\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t\t"+rst.getString(4));
	    System.out.print("\t\t\t\t"+rst.getString(5));
	    System.out.println();*/
	
	    rst.close();
	    preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok(inv).build();
    }

    @Override
    public Response findInventionsByName( @NotNull String name, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomInvention, Entity id, Status, Commencement, Fin from Invention WHERE NomInvention = ?"; 
		Invention inv = new Invention();
    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomInvention\t\tEntity id\t\tStatus\t\tCommencement\t\tFin\n");
		rst.next();
		inv.setName(rst.getString(1));
		inv.setId(rst.getLong(2));
		inv.setStatus(rst.getString(3));
		inv.setStartdate(rst.getString(4));
		inv.setFinsihdate(rst.getString(5));
		
	   /*System.out.print(rst.getString(1));
	   System.out.print("\t\t\t\t\t"+rst.getLong(2));
	   System.out.print("\t\t\t\t\t"+rst.getString(3));
	   System.out.print("\t\t\t\t\t"+rst.getString(4));
	   System.out.print("\t\t\t\t\t"+rst.getString(5));
	   System.out.println();*/
	
	   rst.close();
	   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
    	return Response.ok(inv).build();
    }
    @Override
    public Response findInventionsByStatus( @NotNull String status, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE Status = ?";
		Invention inv = new Invention();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tName\t\tEntityId\t\tStatus\t\tStartdate\t\tFinsihdate\n");
		rst.next();
		inv.setName(rst.getString(1));
		inv.setId(rst.getLong(2));
		inv.setStatus(rst.getString(3));
		inv.setStartdate(rst.getString(4));
		inv.setFinsihdate(rst.getString(5));
			
		/*System.out.print(rst.getString(1));
		System.out.print("\t\t\t\t"+rst.getInt(2));
	    System.out.print("\t\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t\t"+rst.getString(4));
	    System.out.print("\t\t\t\t"+rst.getString(5));
	    System.out.println();*/
		
	    rst.close();
	    preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok(inv).build();
	}

    @Override
    public Response getInventionById(Long inventionId, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE Entity id = ?";
		Invention inv = new Invention();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tName\t\tEntityId\t\tStatus\t\tStartdate\t\tFinsihdate\n");
		rst.next();
		inv.setName(rst.getString(1));
		inv.setId(rst.getLong(2));
		inv.setStatus(rst.getString(3));
		inv.setStartdate(rst.getString(4));
		inv.setFinsihdate(rst.getString(5));
		
		/*System.out.print(rst.getString(1));
	    System.out.print("\t\t\t\t"+rst.getInt(2));
   	    System.out.print("\t\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t\t"+rst.getString(4));
	    System.out.print("\t\t\t\t"+rst.getString(5));
	    System.out.println();*/
		
		rst.close();
		preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok(inv).build();
    }

    @Override
    public Response updateInvention(Invention body, SecurityContext securityContext) throws NotFoundException {
       	String query = "UPDATE Invention SET Name = ?, Status = ?, Startdate = ?, Finsihdate = ? where NomInvention = ? AND Entity id = ?"; 
		
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

    @Override
    public Response updateInventionWithForm(Long inventionId, String name, String status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    public Response uploadFile(Long inventionId, String additionalMetadata, InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
        /*byte[] readFile (String file) {
            ByteArrayOutputStream bos = null;
            try {
                File f = new File(file);
                FileInputStream fis = new FileInputStream(f);
                byte[] buffer = new byte[1024];
                bos = new ByteArrayOutputStream();
                for (int len; (len = fis.read(buffer)) != -1;) {
                    bos.write(buffer, 0, len);
                }
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            } catch (IOException e2) {
                System.err.println(e2.getMessage());
            }
            return bos != null ? bos.toByteArray() : null;
        } 
    	String query = "UPDATE SET Image = ? WHERE Entity id = ?";
         ResultSet rst = null;
 		 try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
	            preparedStmt.setBytes(1, readFile(file));
	            preparedStmt.setLong(1, inventionId);
	            rst = preparedStmt.executeQuery();
	         } catch (SQLException e) {
	             System.out.println(e.getMessage());
	         }
	     }*/
    	 String query = "INSERT INTO Photo (Image, Entity id) values (?, ?)";
    	    String homeDir = System.getProperty("user.home"); 

    	    try (Connection conn = ConnectionManager.getConnection();
					PreparedStatement preparedStmt = conn.prepareStatement(query)){
    	        // set parameters
    	        preparedStmt.setBlob(3, filename);
    	        preparedStmt.setLong(4, inventionId);

    	        preparedStmt.execute();
	        }catch (SQLException e) {
	             System.out.println(e.getMessage());
	        }
            return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
         }
}

 
