package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.User;

import java.util.List;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")
public class UserApiServiceImpl extends UserApiService {
	@Override
	public Response adduser(User body, SecurityContext securityContext) throws NotFoundException {
		String query = "INSERT INTO User(Username, UserId, FirstName, LastName, Email, Phone, Password) VALUES(?,?,?,?,?,?;?)";
		// connection to the DB
		Connection conn = DBManager.getConnection();
		// call the function createId, to generate the Id
		long id = DBManager.createId("User");
		body.setId(id);
		// use of the preparedStatement to execute the query
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, body.getUsername());
			pstmt.setLong(2, body.getId());
			pstmt.setString(3, body.getFirstName());
			pstmt.setString(4, body.getLastName());
			pstmt.setString(5, body.getEmail());
			pstmt.setString(6, body.getPhone());
			pstmt.setString(7, body.getPassword());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// check that Username, First/LastName and password are not null or empty
		assert body.getUsername() != null && !body.getUsername().trim().isEmpty();
		assert body.getFirstName() != null && !body.getFirstName().trim().isEmpty();
		assert body.getLastName() != null && !body.getLastName().trim().isEmpty();
		assert body.getPassword() != null && !body.getPassword().trim().isEmpty();
		return Response.ok().entity(body).build();
	}

	@Override
	public Response deleteUser(String username, SecurityContext securityContext) throws NotFoundException {
		String query = "DELETE FROM User WHERE Username = ?";
		// check that the username given in argument is not null
		assert username != null;
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "user deleted!")).build();
	}

	@Override
	public Response getUserByName(String username, SecurityContext securityContext) throws NotFoundException {
		String query = "SELECT Username, UserId, FirstName, LastName, Email, Phone, Password FROM User WHERE Username = ?";
		User usr = new User();
		// check that the username given in argument is not null
		assert username != null;
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setString(1, username);
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			usr.setUsername(rst.getString(1));
			usr.setId(rst.getLong(2));
			usr.setFirstName(rst.getString(3));
			usr.setLastName(rst.getString(4));
			usr.setEmail(rst.getString(5));
			usr.setPhone(rst.getString(6));
			usr.setPassword(rst.getString(7));
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// check that the username given in argument is the same that the one we are
		// looking for
		assert username.equals(usr.getUsername());
		return Response.ok().entity(usr).build();
	}

	@Override
	public Response loginUser(@NotNull String username, @NotNull String password, SecurityContext securityContext)
			throws NotFoundException {
		// No need to check if username and password (arguments of the method) are null
		// cause define in the method
		// connection to the DB
		Connection conn = DBManager.getConnection();
		String query = "SELECT * FROM User WHERE Username=? and Password=?";
		Boolean status = false;
		try {
			PreparedStatement login = conn.prepareStatement(query);
			login.setString(1, username);
			login.setString(2, password);
			ResultSet rst = login.executeQuery();
			status = rst.next();

		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		return Response.ok().entity(status).build();
	
	}

	@Override
	public Response logoutUser(SecurityContext securityContext) throws NotFoundException {
		// do some magic!
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
	}

	@Override
	public Response updateUser(String username, User body, SecurityContext securityContext) throws NotFoundException {
		String query = "UPDATE User SET Username = ?, FirstName = ?, LastName = ?, Email = ?, Phone = ?, Password = ? where UserId = ?";
		User usr = new User();
		// check that the Id and the username given in argument is not null
		assert body.getId() != null;
		assert username != null;
		// connection to the DB
		Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
			preparedStmt.setString(1, body.getUsername());
			preparedStmt.setString(2, body.getFirstName());
			preparedStmt.setString(3, body.getLastName());
			preparedStmt.setString(4, body.getEmail());
			preparedStmt.setString(5, body.getPhone());
			preparedStmt.setString(6, body.getPassword());
			preparedStmt.executeUpdate();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.entity(DBManager.buildException(e));
			return builder.build();
		}
		// check that the username, First/lastName and password given are not null after
		// update
		assert body.getFirstName() != null;
		assert body.getLastName() != null;
		assert body.getUsername() != null;
		assert body.getPassword() != null;
		return Response.ok().entity(usr).build();
	}

}
