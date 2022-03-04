package io.swagger.api.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import java.util.Date;
import java.util.List;
import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-21T11:04:43.972Z")
public class InventorApiServiceImpl extends InventorApiService {
	public void checkValidDate(String s) {
        assert  s!= null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse ( s );
        } catch (ParseException e) {
            assert false;
        }
    }

    public void checkDateBefore(Date d1, Date d2) {
        long diff=d2.getTime()-d1.getTime();
        assert diff>0;
    }


    @Override
    public Response addInventor(Inventor inventor, SecurityContext securityContext) throws NotFoundException {
    	String query = "INSERT INTO Inventor(Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status) VALUES(?,?,?,?,?,?,?)";

    	Connection conn = ConnectionManager.getConnection();
    	long id=ConnectionManager.createId("Inventor");
        inventor.setId(id);
        assert inventor.getName() == null;
        assert inventor.getBirthdate() == null;
        assert inventor.getDeathdate() == null;
        assert inventor.getNationalite() == null;
        assert inventor.getId() == null;
        assert inventor.getFirstname() == null;
        assert inventor.getStatus() == null;
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
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
	    }
    	 assert inventor.getName() != null && !inventor.getName().trim().isEmpty();
         assert inventor.getBirthdate() != null && !inventor.getBirthdate().trim().isEmpty();
         assert inventor.getFirstname() != null && !inventor.getFirstname().trim().isEmpty();
         checkValidDate(inventor.getBirthdate());
         checkValidDate(inventor.getDeathdate());
        return Response.ok().entity(inventor).build();
    }

    @Override
    public Response deleteInventor(Long inventorId, String apiKey, SecurityContext securityContext) throws NotFoundException {
        String query = "DELETE FROM Inventor WHERE EntityId = ?";
        assert inventorId != null;
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
  	        pstmt.setLong(1, inventorId);
  	        pstmt.executeUpdate();
  	        pstmt.close();
  	    } catch (SQLException e) {
  	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
  	    }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor deleted!")).build();
  	}

    @Override
    public Response findInventorByInvention( @NotNull List<String> invention, SecurityContext securityContext) throws NotFoundException {
		String query = "SELECT * FROM Inventor UNION SELECT * FROM Invention WHERE EntityId.Inventor = EntityId.Invention";
		Inventor inv=new Inventor();
		Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
		preparedStmt.setLong(1, inv.getId());
		ResultSet rst = preparedStmt.executeQuery();
		//System.out.println("tName\t\tBirthdate\t\tDeathdate\t\tNationalite\t\tEntityId\t\tFirstname\t\tStatus\n");
		rst.next();
    	inv.setName(rst.getString(1));
    	inv.setBirthdate(rst.getString(2));
    	inv.setDeathdate(rst.getString(3));
    	inv.setNationalite(rst.getString(4));
    	inv.setId(rst.getLong(5));
    	inv.setFirstname(rst.getString(6));
    	inv.setStatus(rst.getString(7));

	   /*System.out.print(rst.getString(1));
	   System.out.print("\t\t\t"+rst.getString(2));
	   System.out.print("\t\t\t"+rst.getString(3));
	   System.out.print("\t\t\t"+rst.getLong(4));
	   System.out.print("\t\t\t"+rst.getString(5));
	   System.out.print("\t\t\t"+rst.getString(6));
	   System.out.print("\t\t\t"+rst.getString(7));
	   System.out.println();*/

	   //rst.close();
	   //preparedStmt.close();
	   }catch (SQLException e) {
		   e.printStackTrace();
	       ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
           builder.entity(ConnectionManager.buildException(e));
           return builder.build();
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
		/*System.out.println("tName\t\tBirthdate\t\tDeathdate\t\tNationalite\t\tEntityId\t\tFirstname\t\tStatus\n");*/
		rst.next();
	    inv.setName(rst.getString(1));
        inv.setBirthdate(rst.getString(2));
        inv.setDeathdate(rst.getString(3));
        inv.setNationalite(rst.getString(4));
        inv.setId(rst.getLong(5));
        inv.setFirstname(rst.getString(6));
        inv.setStatus(rst.getString(7));

	    /*System.out.print(rst.getString(1));
	    System.out.print("\t\t\t"+rst.getString(2));
	    System.out.print("\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t"+rst.getLong(4));
	    System.out.print("\t\t\t"+rst.getString(5));
	    System.out.print("\t\t\t"+rst.getString(6));
	    System.out.print("\t\t\t"+rst.getString(7));
	    System.out.println();*/

		}catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
		}
    	assert name == inv.getName();
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
		/*System.out.println("tName\t\tBirthdate\t\tDeathdate\t\tNationalite\t\tEntityId\t\tFirstname\t\tStatus\n");*/
		rst.next();
        inv.setName(rst.getString(1));
        inv.setBirthdate(rst.getString(2));
        inv.setDeathdate(rst.getString(3));
        inv.setId(rst.getLong(4));
        inv.setNationalite(rst.getString(5));
        inv.setFirstname(rst.getString(6));
        inv.setStatus(rst.getString(7));

	    /*System.out.print(rst.getString(1));
	    System.out.print("\t\t\t"+rst.getString(2));
	    System.out.print("\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t"+rst.getLong(4));
	    System.out.print("\t\t\t"+rst.getString(5));
	    System.out.print("\t\t\t"+rst.getString(6));
	    System.out.print("\t\t\t"+rst.getString(7));
	    System.out.println();*/

		}catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
		}
    	assert status == inv.getStatus();
    	return Response.ok(inv).build();
    }
    @Override
    public Response getInventorById(Long inventorId, SecurityContext securityContext) throws NotFoundException {
       	String query = "SELECT Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status from Inventor WHERE EntityId = ?";
       	Inventor inv=new Inventor();
       	assert inventorId != null;
       	Connection conn = ConnectionManager.getConnection();
       	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
   		preparedStmt.setLong(1, inventorId);
		ResultSet rst = preparedStmt.executeQuery();
		/*System.out.println("tName\t\tBirthdate\t\tDeathdate\t\tNationalite\t\tEntityId\t\tFirstname\t\tStatus\n");*/
		rst.next();
        inv.setName(rst.getString(1));
        inv.setBirthdate(rst.getString(2));
        inv.setDeathdate(rst.getString(3));
        inv.setNationalite(rst.getString(4));
        inv.setId(rst.getLong(5));
        inv.setFirstname(rst.getString(6));
        inv.setStatus(rst.getString(7));

	    /*System.out.print(rst.getString(1));
	    System.out.print("\t\t\t"+rst.getString(2));
	    System.out.print("\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t"+rst.getLong(4));
	    System.out.print("\t\t\t"+rst.getString(5));
	    System.out.print("\t\t\t"+rst.getString(6));
	    System.out.print("\t\t\t"+rst.getString(7));
	    System.out.println();*/

		}catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
		}
       	assert inventorId == inv.getId();
   		return Response.ok(inv).build();
	}

    @Override
    public Response inventorfindByDate( String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status from Inventor WHERE Birthdate = ?";
    	Inventor inv=new Inventor();
    	assert date != null;
    	checkValidDate(date);
    	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    	preparedStmt.setString(1, date);
		ResultSet rst = preparedStmt.executeQuery();
		/*System.out.println("tName\t\tBirthdate\t\tDeathdate\t\tNationalite\t\tEntityId\t\tFirstname\t\tStatus\n");*/
		rst.next();
        inv.setName(rst.getString(1));
        inv.setBirthdate(rst.getString(2));
        inv.setDeathdate(rst.getString(3));
        inv.setNationalite(rst.getString(4));
        inv.setId(rst.getLong(5));
        inv.setFirstname(rst.getString(6));
        inv.setStatus(rst.getString(7));


 	    /*System.out.print(rst.getString(1));
 	    System.out.print("\t\t\t"+rst.getString(2));
	    System.out.print("\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t"+rst.getLong(4));
	    System.out.print("\t\t\t"+rst.getString(5));
	    System.out.print("\t\t\t"+rst.getString(6));
	    System.out.print("\t\t\t"+rst.getString(7));
	    System.out.println();*/

		}catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
		}
    	assert date == inv.getBirthdate();
    	return Response.ok(inv).build();
	}

    @Override
    public Response updateInventor(Inventor inventor, SecurityContext securityContext) throws NotFoundException {
    	String query = "UPDATE Inventor SET Name = ?, Birthdate = ?, Deathdate = ?, Nationalite = ?, Firstname = ?, Status = ? where EntityId = ?";
    	assert inventor.getId() != null;
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
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
		}
			assert inventor.getBirthdate() != null;
			assert inventor.getFirstname() != null;
			assert inventor.getName() != null;
      checkValidDate(inventor.getBirthdate());
      checkValidDate(inventor.getDeathdate());
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
     assert inventorId != null;
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
