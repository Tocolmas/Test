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

	@Override
	public Response addInvention(Invention body, SecurityContext securityContext) throws NotFoundException {
		String query = "INSERT INTO Invention(Name, EntityId, Status, Startdate, Finsihdate) VALUES(?,?,?,?,?)";
		// connection to the DB
		Connection conn = DBManager.getConnection();
		// call the function createId, to generate the Id
		long id = DBManager.createId("Invention");
		body.setId(id);
		// use of the preparedStatement to execute the query
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, body.getName());
			pstmt.setString(2, body.getStatus());
			pstmt.setString(3, body.getStartdate());
			pstmt.setString(4, body.getFinsihdate());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// check that Name, Startdate are not null or empty
		assert body.getName() != null && !body.getName().trim().isEmpty();
		assert body.getStartdate() != null && !body.getStartdate().trim().isEmpty();
		// check that the dates respect the right format
		checkValidDate(body.getStartdate());
		checkValidDate(body.getFinsihdate());

		return Response.ok().entity(body).build();
	}

	@Override
	public Response deleteInvention(Long inventionId, String apiKey, SecurityContext securityContext)
			throws NotFoundException {
		String query = "DELETE FROM Invention WHERE EntityId = ?";
		// verify that the id we are trying to delete information from exist
		assert inventionId != null;
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setLong(1, inventionId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Invention deleted!")).build();
	}

	@Override
	public Response findByDate(String date, SecurityContext securityContext) throws NotFoundException {
		String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE Startdate = ?";
		Invention inv = new Invention();
		//verify that the date given in arguments is not null and check that it is written in the right format
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
			inv.setId(rst.getLong(2));
			inv.setStatus(rst.getString(3));
			inv.setStartdate(rst.getString(4));
			inv.setFinsihdate(rst.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// verify that the date given in argument is the same than the one we are looking for and check the format
		assert date == inv.getStartdate();
		checkValidDate(inv.getStartdate());
		return Response.ok(inv).build();
	}

	@Override
	public Response findInventionByInventor(@NotNull List<String> inventor, SecurityContext securityContext)
			throws NotFoundException {
		// no need to verify that the List<String> is not null, because we have it verify in the parameters of the method 
		String query = "SELECT * FROM Invention UNION SELECT * FROM INVENTOR WHERE EntityId.Invention = EntityId.Inventor";
		Invention inv = new Invention();
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			inv.setName(rst.getString(1));
			inv.setId(rst.getLong(2));
			inv.setStatus(rst.getString(3));
			inv.setStartdate(rst.getString(4));
			inv.setFinsihdate(rst.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		return Response.ok(inv).build();
	}

	@Override
	public Response findInventionBysByTags(@NotNull List<String> tags, String date, SecurityContext securityContext)
			throws NotFoundException {
		// no need to verify that the List<String> is not null, because we have it verify in the parameters of the method 
		String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE EntityId = ?";
		Invention inv = new Invention();
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setArray(1, (Array) tags);
			// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			inv.setName(rst.getString(1));
			inv.setId(rst.getLong(2));
			inv.setStatus(rst.getString(3));
			inv.setStartdate(rst.getString(4));
			inv.setFinsihdate(rst.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		return Response.ok(inv).build();
	}

	@Override
	public Response findInventionsByName(@NotNull String name, SecurityContext securityContext)
			throws NotFoundException {
		// no need to verify that the name given in argument is not null, because we have it verify in the parameters of the method 
		String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate from Invention WHERE Name = ?";
		Invention inv = new Invention();
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setString(1, name);
			// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			inv.setName(rst.getString(1));
			inv.setId(rst.getLong(2));
			inv.setStatus(rst.getString(3));
			inv.setStartdate(rst.getString(4));
			inv.setFinsihdate(rst.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// verify that the name given in arguments is the same that the one we where looking for
		assert name == inv.getName();
		return Response.ok(inv).build();
	}

	@Override
	public Response findInventionsByStatus(@NotNull String status, SecurityContext securityContext)
			throws NotFoundException {
		// no need to verify that the status given in argument is not null, because we have it verify in the parameters of the method 
		String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE Status = ?";
		Invention inv = new Invention();
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setString(1, status);
			// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			inv.setName(rst.getString(1));
			inv.setId(rst.getLong(2));
			inv.setStatus(rst.getString(3));
			inv.setStartdate(rst.getString(4));
			inv.setFinsihdate(rst.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// verify that the status given in arguments is the same that the one we where looking for
		assert status == inv.getStatus();
		return Response.ok(inv).build();
	}

	@Override
	public Response getInventionById(Long inventionId, SecurityContext securityContext) throws NotFoundException {
		String query = "SELECT Name, EntityId, Status, Startdate, Finsihdate FROM Invention WHERE EntityId = ?";
		Invention inv = new Invention();
		// verify that the Id given in arguments is not null
		assert inventionId != null;
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setLong(1, inventionId);
			// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			inv.setName(rst.getString(1));
			inv.setId(rst.getLong(2));
			inv.setStatus(rst.getString(3));
			inv.setStartdate(rst.getString(4));
			inv.setFinsihdate(rst.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// verify that the Id given in arguments is the same that the one we where looking for
		assert inventionId == inv.getId();
		return Response.ok(inv).build();
	}

	@Override
	public Response updateInvention(Invention body, SecurityContext securityContext) throws NotFoundException {
		String query = "UPDATE Invention SET Name = ?, Status = ?, Startdate = ?, Finsihdate = ? WHERE EntityId = ?";
		// verify that the Id given in arguments is not null
		assert body.getId() != null;
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setString(1, body.getName());
			preparedStmt.setString(2, body.getStatus());
			preparedStmt.setString(2, body.getStartdate());
			preparedStmt.setString(2, body.getFinsihdate());
			preparedStmt.executeUpdate();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// verify that Name and Startdate are not null after the update
		assert body.getName() != null;
		assert body.getStartdate() != null;
		// check that Stardate and finishdate are writthen in the right format
		checkValidDate(body.getStartdate());
		checkValidDate(body.getFinsihdate());
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
	}

	@Override
	public Response updateInventionWithForm(Long inventionId, String name, String status,
			SecurityContext securityContext) throws NotFoundException {
		String query = "UPDATE Invention SET Name = ?, Status = ? where EntityId = ?";
		// verify that the name, the Id and the status given in arguments is not null
		assert inventionId != null;
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
		return Response.ok().entity(inventionId).build();
	}

	public Response uploadFile(Long inventionId, String additionalMetadata, InputStream fileInputStream,
			FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
		String query = "INSERT INTO Photo(Image, PhotoId) values (?,?)";
		String homeDir = System.getProperty("user.home");
		String filename = "invention-" + inventionId + ".jpg";
		File file = new File("images", filename);
		// verify that the Id given in arguments is not null
		assert inventionId != null;
		Connection conn = DBManager.getConnection();
		try (FileOutputStream fout = new FileOutputStream(file);
				PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			// set parameters
			preparedStmt.setString(1, filename);
			preparedStmt.setLong(2, inventionId);
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
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
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
