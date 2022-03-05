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
	// verify that the format of the date is respected
	public void checkValidDate(String s) {
		assert s != null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = format.parse(s);
		} catch (ParseException e) {
			assert false;
		}
	}
	// verify that the birthdate is before the deathdate
	public void checkDateBefore(Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		assert diff > 0;
	}

	@Override
	public Response addInventor(Inventor inventor, SecurityContext securityContext) throws NotFoundException {
		// the query SQLite asked
		String query = "INSERT INTO Inventor(Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status) VALUES(?,?,?,?,?,?,?)";
		// connection to the DB
		Connection conn = DBManager.getConnection();
		// call the function createId, to generate the Id
		long id = DBManager.createId("Inventor");
		inventor.setId(id);
		// use of the preparedStatement to gather the information and send it into the DB
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, inventor.getName());
			pstmt.setString(2, inventor.getBirthdate());
			pstmt.setString(3, inventor.getDeathdate());
			pstmt.setString(4, inventor.getNationalite());
			pstmt.setLong(5, inventor.getId());
			pstmt.setString(6, inventor.getFirstname());
			pstmt.setString(7, inventor.getStatus());
			pstmt.executeUpdate();
			pstmt.close();
		// catch an exception, if there is one
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// check that Name, Firstname and birthdate are not null or empty
		assert inventor.getName() != null && !inventor.getName().trim().isEmpty();
		assert inventor.getBirthdate() != null && !inventor.getBirthdate().trim().isEmpty();
		assert inventor.getFirstname() != null && !inventor.getFirstname().trim().isEmpty();
		// verify that the date is written in the right format
		checkValidDate(inventor.getBirthdate());
		return Response.ok().entity(inventor).build();
	}

	@Override
	public Response deleteInventor(Long inventorId, String apiKey, SecurityContext securityContext)
			throws NotFoundException {
		// the query SQLite asked
		String query = "DELETE FROM Inventor WHERE EntityId = ?";
		// assure that the Id we want to delete, do exist
		assert inventorId != null;
		// connection to the DB
		Connection conn = DBManager.getConnection();
		// Use of the prepareStatement to execute the query
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setLong(1, inventorId);
			pstmt.executeUpdate();
			pstmt.close();
		// catch an exception, if there is one
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor deleted!")).build();
	}

	@Override
	public Response findInventorByInvention(@NotNull List<String> invention, SecurityContext securityContext)
			throws NotFoundException {
		// no need to verify that the List<String> is not null, because we have it verify in the parameters of the method 
		// the query SQLite asked
		String query = "SELECT * FROM Inventor UNION SELECT * FROM Invention WHERE EntityId.Inventor = EntityId.Invention";
		Inventor inv = new Inventor();
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setLong(1, inv.getId());
			// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			inv.setName(rst.getString(1));
			inv.setBirthdate(rst.getString(2));
			inv.setDeathdate(rst.getString(3));
			inv.setNationalite(rst.getString(4));
			inv.setId(rst.getLong(5));
			inv.setFirstname(rst.getString(6));
			inv.setStatus(rst.getString(7));
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		return Response.ok(inv).build();
	}

	@Override
	public Response findInventorsByName(@NotNull String name, SecurityContext securityContext)
			throws NotFoundException {
		// no need to verify that the List<String> is not null, because we have it verify in the parameters of the method 
		String query = "SELECT Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status FROM Inventor WHERE Name = ?";
		Inventor inv = new Inventor();
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setString(1, name);
			// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			inv.setName(rst.getString(1));
			inv.setBirthdate(rst.getString(2));
			inv.setDeathdate(rst.getString(3));
			inv.setNationalite(rst.getString(4));
			inv.setId(rst.getLong(5));
			inv.setFirstname(rst.getString(6));
			inv.setStatus(rst.getString(7));
			   
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// verify that the name given in arguments is the same that the one we where looking for
		assert name .equals(inv.getName());
		return Response.ok(inv).build();
	}

	@Override
	public Response findInventorsByStatus(@NotNull String status, SecurityContext securityContext)
			throws NotFoundException {
		// no need to verify that the List<String> is not null, because we have it verify in the parameters of the method
		String query = "SELECT Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status from Inventor WHERE Status = ?";
		Inventor inv = new Inventor();
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setString(1, status);
			// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			inv.setName(rst.getString(1));
			inv.setBirthdate(rst.getString(2));
			inv.setDeathdate(rst.getString(3));
			inv.setId(rst.getLong(4));
			inv.setNationalite(rst.getString(5));
			inv.setFirstname(rst.getString(6));
			inv.setStatus(rst.getString(7));
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		//verify that the status given in argument is the same that the one we are looking for
		assert status .equals(inv.getStatus());
		return Response.ok(inv).build();
	}

	@Override
	public Response getInventorById(Long inventorId, SecurityContext securityContext) throws NotFoundException {
		String query = "SELECT Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status from Inventor WHERE EntityId = ?";
		Inventor inv = new Inventor();
		//verify that we are not trying to find an inventor that does not exist
		assert inventorId != null;
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setLong(1, inventorId);
			// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			inv.setName(rst.getString(1));
			inv.setBirthdate(rst.getString(2));
			inv.setDeathdate(rst.getString(3));
			inv.setNationalite(rst.getString(4));
			inv.setId(rst.getLong(5));
			inv.setFirstname(rst.getString(6));
			inv.setStatus(rst.getString(7));
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// verify that the id given in argument is the same that the one we are looking for 
		assert inventorId == inv.getId();
		return Response.ok(inv).build();
	}

	@Override
	public Response inventorfindByDate(String date, SecurityContext securityContext) throws NotFoundException {
		String query = "SELECT Name, Birthdate, Deathdate, Nationalite, EntityId, Firstname, Status from Inventor WHERE Birthdate = ?";
		Inventor inv = new Inventor();
		// verify that the date given in argument is not null and that it is written the right way 
		assert date != null;
		checkValidDate(date);
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setString(1, date);
			// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			inv.setName(rst.getString(1));
			inv.setBirthdate(rst.getString(2));
			inv.setDeathdate(rst.getString(3));
			inv.setNationalite(rst.getString(4));
			inv.setId(rst.getLong(5));
			inv.setFirstname(rst.getString(6));
			inv.setStatus(rst.getString(7));
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// verify that the date given in argument is the same that the one we are looking for
		assert date.equals(inv.getBirthdate());
		return Response.ok(inv).build();
	}

	@Override
	public Response updateInventor(Inventor inventor, SecurityContext securityContext) throws NotFoundException {
		String query = "UPDATE Inventor SET Name = ?, Birthdate = ?, Deathdate = ?, Nationalite = ?, Firstname = ?, Status = ? where EntityId = ?";
		// verify that the id given in argument is not null
		assert inventor.getId() != null;
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setString(1, inventor.getName());
			preparedStmt.setString(2, inventor.getBirthdate());
			preparedStmt.setString(3, inventor.getDeathdate());
			preparedStmt.setString(4, inventor.getNationalite());
			preparedStmt.setString(5, inventor.getFirstname());
			preparedStmt.setString(6, inventor.getStatus());
			preparedStmt.executeUpdate();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// verify that the new Name, Firstname, Birthdate are not null
		assert inventor.getBirthdate() != "null";
		assert inventor.getFirstname() != "null";
		assert inventor.getName() != "null";
		// verify that the date is written in the right format
		checkValidDate(inventor.getBirthdate());
		return Response.ok().entity(inventor).build();
	}

	@Override
	public Response updateInventorWithForm(Long inventorId, String name, String status, SecurityContext securityContext)
			throws NotFoundException {
		String query = "UPDATE Inventor SET Name = ?, Status = ? where EntityId = ?";
		//verify that the arguments of the method are not null
		assert inventorId != null;
		assert name != null;
		assert status != null;
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, status);
			preparedStmt.executeUpdate();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		return Response.ok().entity(inventorId).build();
	}

	@Override
	public Response uploadImage(Long inventorId, String additionalMetadata, InputStream fileInputStream,
			FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
		String query = "INSERT INTO Photo(Image, PhotoId) values (?,?)";
		String homeDir = System.getProperty("user.home");
		String filename = "inventor-" + inventorId + ".jpg";
		File file = new File("images", filename);
		//verify that the Id is not null
		assert inventorId != null;
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (FileOutputStream fout = new FileOutputStream(file);
				PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			// set parameters
			preparedStmt.setString(1, filename);
			preparedStmt.setLong(2, inventorId);
			preparedStmt.execute();

			// read stream into file
			int BUFF_SIZE = 1024;
			byte[] buff = new byte[BUFF_SIZE];
			int read;
			while ((read = fileInputStream.read(buff)) != -1) {
				fout.write(buff, 0, read);
			}
		} catch (SQLException e) {
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
