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
    	String query = "INSERT INTO Inventor(Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status) VALUES(?,?,?,?,?,?,?)";
    	
    	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, inventor.getName());
	        pstmt.setString(2, inventor.getBirthdate());
	        pstmt.setString(3, inventor.getDeathdate());
	        pstmt.setString(4, inventor.getNationalite());
	        pstmt.setLong(5,  inventor.getId());
	        pstmt.setString(5, inventor.getFirstname());
	        pstmt.setString(6, inventor.getStatus());
	        pstmt.executeUpdate(); 
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    	return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "New inventor added")).build();
    }

    @Override
    public Response deleteInventor(Long inventorId, String apiKey, SecurityContext securityContext) throws NotFoundException {
        String query = "DELETE FROM Inventor WHERE EntityId = ?";
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
  	        pstmt.setLong(1, inventorId);
  	        pstmt.executeUpdate();
  	        pstmt.close();
  	    } catch (SQLException e) {
  	        e.printStackTrace();
  	    }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor deleted!")).build();
  	}

    @Override
    public Response findInventorByInvention( @NotNull List<String> invention, SecurityContext securityContext) throws NotFoundException {
		String query = "SELECT Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status from Inventor WHERE EntityId = ? UNION SELECT * FROM Invention WHERE EntityId = ? ORDER BY EntityId.Inventor"; 
		Inventor inv=new Inventor();
		Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tName\t\tBirthdate\t\tDeathdate\t\tNationalite\t\tEntityId\t\tFirstname\t\tStatus\n");
		rst.next();
    	inv.setName(rst.getString(1));
    	inv.setBirthdate(rst.getString(2));
    	inv.setDeathdate(rst.getString(3)); 
    	inv.setNationalite(rst.getString(4));
    	inv.setId(rst.getLong(5));
    	inv.setFirstname(rst.getString(6));
    	inv.setStatus(rst.getString(7));
    	
	    preparedStmt.setLong(1, inv.getId());
	
	   System.out.print(rst.getString(1));
	   System.out.print("\t\t\t"+rst.getString(2));
	   System.out.print("\t\t\t"+rst.getString(3));
	   System.out.print("\t\t\t"+rst.getLong(4));
	   System.out.print("\t\t\t"+rst.getString(5));
	   System.out.print("\t\t\t"+rst.getString(6)); 
	   System.out.print("\t\t\t"+rst.getString(7));
	   System.out.println();
		
	   //rst.close();
	   //preparedStmt.close();
	   }catch (SQLException e) {
		   e.printStackTrace();
	   }
       return Response.ok(inv).build();
	}

    @Override
    public Response findInventorsByName( @NotNull String name, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status FROM Inventor WHERE Name = ?";
    	Inventor inv=new Inventor();
    	
    	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    	preparedStmt.setString(1, name);

		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tName\t\tBirthdate\t\tDeathdate\t\tNationalite\t\tEntityId\t\tFirstname\t\tStatus\n");
		rst.next();
	    inv.setName(rst.getString(1));
        inv.setBirthdate(rst.getString(2));
        inv.setDeathdate(rst.getString(3));
        inv.setNationalite(rst.getString(4));
        inv.setId(rst.getLong(5));
        inv.setFirstname(rst.getString(6));
        inv.setStatus(rst.getString(7));
        
	    System.out.print(rst.getString(1));
	    System.out.print("\t\t\t"+rst.getString(2));
	    System.out.print("\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t"+rst.getLong(4));
	    System.out.print("\t\t\t"+rst.getString(5));
	    System.out.print("\t\t\t"+rst.getString(6));
	    System.out.print("\t\t\t"+rst.getString(7));
	    System.out.println();
		   
//	    rst.close();
//	    preparedStmt.close();
	   
		}catch (SQLException e) {
	        e.printStackTrace();
		}
    	return Response.ok(inv).build();
    }

    @Override
    public Response findInventorsByStatus( @NotNull String status, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status from Inventor WHERE Status = ?";
    	Inventor inv=new Inventor();
    	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    	preparedStmt.setString(1, status);
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tName\t\tBirthdate\t\tDeathdate\t\tNationalite\t\tEntityId\t\tFirstname\t\tStatus\n");
		rst.next();
        inv.setName(rst.getString(1));
        inv.setBirthdate(rst.getString(2));
        inv.setDeathdate(rst.getString(3));
        inv.setId(rst.getLong(4));
        inv.setNationalite(rst.getString(5));
        inv.setFirstname(rst.getString(6));
        inv.setStatus(rst.getString(7));
        
	    System.out.print(rst.getString(1));
	    System.out.print("\t\t\t"+rst.getString(2));
	    System.out.print("\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t"+rst.getLong(4));
	    System.out.print("\t\t\t"+rst.getString(5));
	    System.out.print("\t\t\t"+rst.getString(6));
	    System.out.print("\t\t\t"+rst.getString(7));
	    System.out.println();
//	   rst.close();
//	   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "inventor found!")).build();
    }
    @Override
    public Response getInventorById(Long inventorId, SecurityContext securityContext) throws NotFoundException {
       	String query = "SELECT Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status from Inventor WHERE EntityId = ?";
       	Inventor inv=new Inventor();
       	Connection conn = ConnectionManager.getConnection();
       	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
   		preparedStmt.setLong(1, inventorId);
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tName\t\tBirthdate\t\tDeathdate\t\tNationalite\t\tEntityId\t\tFirstname\t\tStatus\n");
		rst.next(); 
        inv.setName(rst.getString(1));
        inv.setBirthdate(rst.getString(2));
        inv.setDeathdate(rst.getString(3));
        inv.setNationalite(rst.getString(4));
        inv.setId(rst.getLong(5));
        inv.setFirstname(rst.getString(6));
        inv.setStatus(rst.getString(7));
 	        
	    System.out.print(rst.getString(1));
	    System.out.print("\t\t\t"+rst.getString(2));
	    System.out.print("\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t"+rst.getLong(4));
	    System.out.print("\t\t\t"+rst.getString(5));
	    System.out.print("\t\t\t"+rst.getString(6));
	    System.out.print("\t\t\t"+rst.getString(7));
	    System.out.println();
	
	   //rst.close();
	   //preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
   		return Response.ok(inv).build();
	}
 
    @Override
    public Response inventorfindByDate( String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status from Inventor WHERE Birthdate = ?"; 
    	Inventor inv=new Inventor();
    	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    	preparedStmt.setString(1, date);
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tName\t\tBirthdate\t\tDeathdate\t\tNationalite\t\tEntityId\t\tFirstname\t\tStatus\n");
		rst.next();
        inv.setName(rst.getString(1));
        inv.setBirthdate(rst.getString(2));
        inv.setDeathdate(rst.getString(3));
        inv.setNationalite(rst.getString(4));
        inv.setId(rst.getLong(5));
        inv.setFirstname(rst.getString(6));
        inv.setStatus(rst.getString(7));
       
        
 	    System.out.print(rst.getString(1));
 	    System.out.print("\t\t\t"+rst.getString(2));
	    System.out.print("\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t"+rst.getLong(4));
	    System.out.print("\t\t\t"+rst.getString(5));
	    System.out.print("\t\t\t"+rst.getString(6));
	    System.out.print("\t\t\t"+rst.getString(7));
	    System.out.println();
		
		//   rst.close();
		  // preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
    	return Response.ok(inv).build();
	}

    @Override
    public Response updateInventor(Inventor inventor, SecurityContext securityContext) throws NotFoundException {
    	String query = "UPDATE Inventor SET Name = ?, Birthdate = ?, Deathdate = ?, Nationalite = ?, Firstname = ?, Status = ? where EntityId = ?"; 
    	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
	    preparedStmt.setString(1, inventor.getName());
	    preparedStmt.setString(2, inventor.getBirthdate());
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
   	 String query = "INSERT INTO Photo(Image, PhotoId) values (?,?)";
     String homeDir = System.getProperty("user.home"); 
     String filename = "inventor-"+inventorId+".jpg";
     File file=new File("images",filename);
     Connection conn = ConnectionManager.getConnection();
     try (   FileOutputStream fout=new FileOutputStream(file);
	 		 PreparedStatement preparedStmt = conn.prepareStatement(query)){
         // set parameters
         preparedStmt.setString(1, filename);
         preparedStmt.setLong(2, inventorId);       
         preparedStmt.execute();
         
         // read stream into file
         int BUFF_SIZE=1024;
         byte[] buff=new byte[BUFF_SIZE];
         int read;
         while((read=fileInputStream.read(buff))!=-1) {
        	 fout.write(buff,0,read);
         }
     }catch (SQLException e) {
    	e.printStackTrace();
  } catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
     return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
  }
}
