package io.swagger.api.impl;
import java.sql.Array;
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
import io.swagger.model.Invention;
import io.swagger.model.ModelApiResponse;

import java.util.Date;
import java.util.List;
import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-21T11:04:43.972Z")
public class InventionApiServiceImpl extends InventionApiService {
	public void checkValidDate(String s) {
        assert  s!= null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse ( s );
        } catch (ParseException e) {
            assert false;
        }
    }
	@Override
    public Response addInvention(Invention body, SecurityContext securityContext) throws NotFoundException {
    	  String query = "INSERT INTO Invention(Name, EntityId, Status, Startdate, Finsihdate) VALUES(?,?,?,?,?)";
        assert body.getName() == null;
        assert body.getId() == null;
        assert body.getStartdate() == null;
        assert body.getFinsihdate() == null;
        assert body.getStatus() == null;
    	Connection conn = ConnectionManager.getConnection();
			long id=ConnectionManager.createId("Invention");
			body.setId(id);
 	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
 	        pstmt.setString(1, body.getName());
 	        pstmt.setString(2, body.getStatus());
 	        pstmt.setString(3, body.getStartdate());
 	        pstmt.setString(4, body.getFinsihdate());
 	        pstmt.executeUpdate();
 	        pstmt.close();
 	    }catch (SQLException e) {
 	       e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
 	    }
	   	 assert body.getName() != null && !body.getName().trim().isEmpty();
	     assert body.getStartdate() != null && !body.getStartdate().trim().isEmpty();
	   	 checkValidDate(body.getStartdate());
		 checkValidDate(body.getFinsihdate());

	     return Response.ok().entity(body).build();
     }

    @Override
    public Response deleteInvention(Long inventionId, String apiKey, SecurityContext securityContext) throws NotFoundException {
    	String query = "DELETE FROM Invention WHERE EntityId = ?";
    	assert inventionId != null;
    	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setLong(1, inventionId);
	        pstmt.executeUpdate();
	        pstmt.close();
	    	} catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
	    	}
	    	return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Invention deleted!")).build();
	    }

    @Override
    public Response findByDate( String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE Startdate = ?";
		Invention inv = new Invention();
		assert date != null;
		checkValidDate(date);
		Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
			preparedStmt.setString(1, date);
			ResultSet rst = preparedStmt.executeQuery();
			/*System.out.println("tName\t\tEntityId\t\tStatus\t\tStartdate\t\tFinsihdate\n");*/
			rst.next();
			inv.setName(rst.getString(1));
			inv.setId(rst.getLong(2));
			inv.setStatus(rst.getString(3));
			inv.setStartdate(rst.getString(4));
			inv.setFinsihdate(rst.getString(5));

			/*System.out.print("\t\t\t"+rst.getString(1));
			System.out.print("\t\t\t"+rst.getLong(2));
			System.out.print("\t\t\t"+rst.getInt(3));
		    System.out.print("\t\t\t"+rst.getString(4));
		    System.out.print("\t\t\t"+rst.getString(5));
		    System.out.println();*/

			}catch (SQLException e) {
		        e.printStackTrace();
		        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
	            builder.entity(ConnectionManager.buildException(e));
	            return builder.build();
			}
			assert date == inv.getStartdate();
			checkValidDate(inv.getStartdate());
			return Response.ok(inv).build();
	}

    @Override
    public Response findInventionByInventor( @NotNull List<String> inventor, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT * FROM Invention UNION SELECT * FROM INVENTOR WHERE EntityId.Invention = EntityId.Inventor";
		Invention inv = new Invention();
		Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
			ResultSet rst = preparedStmt.executeQuery();
			/*System.out.println("tName\t\tEntityId\t\tStatus\t\tStartdate\t\tFinsihdate\n");*/
			rst.next();
			inv.setName(rst.getString(1));
			inv.setId(rst.getLong(2));
			inv.setStatus(rst.getString(3));
			inv.setStartdate(rst.getString(4));
			inv.setFinsihdate(rst.getString(5));

			/*System.out.print("\t\t\t"+rst.getString(1));
			System.out.print("\t\t\t"+rst.getLong(2));
		    System.out.print("\t\t\t"+rst.getString(3));
		    System.out.print("\t\t\t"+rst.getString(4));
		    System.out.print("\t\t\t"+rst.getString(5));
		    System.out.println();*/

			}catch (SQLException e) {
		        e.printStackTrace();
		        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
	            builder.entity(ConnectionManager.buildException(e));
	            return builder.build();
			}
			return Response.ok(inv).build();
    	}

    @Override
    public Response findInventionBysByTags( @NotNull List<String> tags,  String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE EntityId = ?";
		Invention inv = new Invention();
		Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
			preparedStmt.setArray(1, (Array) tags);
			ResultSet rst = preparedStmt.executeQuery();
			//System.out.println("tName\t\tEntityId\t\tStatus\t\tStartdate\t\tFinsihdate\n");
			rst.next();
			inv.setName(rst.getString(1));
			inv.setId(rst.getLong(2));
			inv.setStatus(rst.getString(3));
			inv.setStartdate(rst.getString(4));
			inv.setFinsihdate(rst.getString(5));

			/*System.out.print(rst.getString(1));
			System.out.print("\t\t\t"+rst.getLong(2));
		    System.out.print("\t\t\t"+rst.getString(3));
		    System.out.print("\t\t\t"+rst.getString(4));
		    System.out.print("\t\t\t"+rst.getString(5));
		    System.out.println();*/

			}catch (SQLException e) {
		        e.printStackTrace();
		        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
	            builder.entity(ConnectionManager.buildException(e));
	            return builder.build();
			}
			return Response.ok(inv).build();
	    }

    @Override
    public Response findInventionsByName( @NotNull String name, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate from Invention WHERE Name = ?";
		Invention inv = new Invention();
		Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    		preparedStmt.setString(1, name);
			ResultSet rst = preparedStmt.executeQuery();
			//System.out.println("tName\t\tEntityId\t\tStatus\t\tStartdate\t\tFinsihdate\n");
			rst.next();
			inv.setName(rst.getString(1));
			inv.setId(rst.getLong(2));
			inv.setStatus(rst.getString(3));
			inv.setStartdate(rst.getString(4));
			inv.setFinsihdate(rst.getString(5));

		   /*System.out.print(rst.getString(1));
		   System.out.print("\t\t\t"+rst.getLong(2));
		   System.out.print("\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t"+rst.getString(4));
		   System.out.print("\t\t\t"+rst.getString(5));
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
    public Response findInventionsByStatus( @NotNull String status, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE Status = ?";
		Invention inv = new Invention();
		Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
			preparedStmt.setString(1, status);
			ResultSet rst = preparedStmt.executeQuery();
			//System.out.println("tName\t\tEntityId\t\tStatus\t\tStartdate\t\tFinsihdate\n");
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
    public Response getInventionById(Long inventionId, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE EntityId = ?";
		Invention inv = new Invention();
		assert inventionId != null;
		Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
			preparedStmt.setLong(1, inventionId);
			ResultSet rst = preparedStmt.executeQuery();
			//System.out.println("tName\t\tEntityId\t\tStatus\t\tStartdate\t\tFinsihdate\n");
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

			}catch (SQLException e) {
		        e.printStackTrace();
		        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
	            builder.entity(ConnectionManager.buildException(e));
	            return builder.build();
			}
			assert inventionId == inv.getId();
			return Response.ok(inv).build();
	    }

    @Override
    public Response updateInvention(Invention body, SecurityContext securityContext) throws NotFoundException {
       	String query = "UPDATE Invention SET Name = ?, Status = ?, Startdate = ?, Finsihdate = ? WHERE EntityId = ?";
       	assert body.getId() != null;

       	Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
	    preparedStmt.setString(1, body.getName());
	    preparedStmt.setString(2, body.getStatus());
	    preparedStmt.setString(2, body.getStartdate());
	    preparedStmt.setString(2, body.getFinsihdate());
	    preparedStmt.executeUpdate();
	    preparedStmt.close();
		}catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
		}
		assert body.getName() != null;
		assert body.getStartdate() != null;
	  checkValidDate(body.getStartdate());
		checkValidDate(body.getFinsihdate());
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response updateInventionWithForm(Long inventionId, String name, String status, SecurityContext securityContext) throws NotFoundException {
			String query = "UPDATE Invention SET Name = ?, Status = ? where EntityId = ?";
    	assert inventionId != null;
    	assert name != null;
    	assert status != null;
    	Connection conn = DBManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    		preparedStmt.setString(1, name);
    		preparedStmt.setString(2, status);
		    preparedStmt.executeUpdate();
		    preparedStmt.close();
    	}catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(DBManager.buildException(e));
            return builder.build();
		}
    	return Response.ok().entity(inventionId).build();
    }

    public Response uploadFile(Long inventionId, String additionalMetadata, InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
      	 String query = "INSERT INTO Photo(Image, PhotoId) values (?,?)";
         String homeDir = System.getProperty("user.home");
         String filename = "invention-"+inventionId+".jpg";
         File file=new File("images",filename);
         assert inventionId != null;
         Connection conn = ConnectionManager.getConnection();
         try (   FileOutputStream fout=new FileOutputStream(file);
    	 		 PreparedStatement preparedStmt = conn.prepareStatement(query)){
             // set parameters
             preparedStmt.setString(1, filename);
             preparedStmt.setLong(2, inventionId);
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
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
      } catch (FileNotFoundException e1) {
    		// TODO Auto-generated catch block
    	} catch (IOException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
         return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
      }
}
