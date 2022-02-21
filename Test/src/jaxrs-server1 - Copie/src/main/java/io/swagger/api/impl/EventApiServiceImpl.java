package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Event;
import io.swagger.model.Inventor;

import java.util.List;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-19T09:33:27.588Z")
public class EventApiServiceImpl extends EventApiService {
    @Override
    public Response addevent(Event events, SecurityContext securityContext) throws NotFoundException {
    	String query = "INSERT INTO Event(NomEvent, DateDebut, DebutFin) VALUES(?)";

	    try (Connection conn = ConnectionManager.getConnection(); // TODO verifier si on garde getConnection ici
	         PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, events.getName());
	        pstmt.setString(2, events.getStartdate());
	        pstmt.setString(3, events.getEnddate());
	        pstmt.executeUpdate();
	        pstmt.close();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "new event added")).build();
    }
    @Override
    public Response deleteEvent(Long eventId, String apiKey, SecurityContext securityContext) throws NotFoundException {
    	String query = "DELETE FROM Event WHERE Entity id = ?";
    	try (Connection conn = ConnectionManager.getConnection();
  	            PreparedStatement pstmt = conn.prepareStatement(query)) {
  	        pstmt.setLong(1, eventId);
  	        pstmt.executeUpdate();
  	        pstmt.close();
  	    } catch (SQLException e) {
  	        System.out.println(e.getMessage());
  	    }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event deleted!")).build();
    }
    @Override
    public Response eventfindByDate( String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomEvent, Event id, DateDebut, DateFin FROM Event WHERE DateDebut = ?";

    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomEvent\t\tEvent id\t\tDateDebut\t\tDateFin\n");
		while(rst.next()) {
		   System.out.print("\t\t\t"+rst.getString(1));
		   System.out.print("\t\t\t"+rst.getLong(2));
		   System.out.print("\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t"+rst.getString(4));
		   System.out.println();
		}
		   rst.close();
		   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response findEventsByInvention( @NotNull List<String> event, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT * FROM (SELECT NomEvent, Event id, DateDebut, DateFin FROM Event WHERE Event id = ? UNION SELECT Event id, Entity id FROM Participation WHERE Event id = ? and Enity id = ? UNION SELECT Entity id FROM Invention Where Entity id = ?";

    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomEvent\t\tEvent id\t\tDateDebut\t\tDateFin\n");
		while(rst.next()) {
		   System.out.print("\t\t\t"+rst.getString(1));
		   System.out.print("\t\t\t"+rst.getLong(2));
		   System.out.print("\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t"+rst.getString(4));
		   System.out.println();
		}
		   rst.close();
		   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event found!")).build();
	}

    @Override
    public Response findEventsByInventor( @NotNull List<String> event, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT * FROM (SELECT NomEvent, Event id, DateDebut, DateFin FROM Event WHERE Event id = ? UNION SELECT Event id, Entity id FROM Participation WHERE Event id = ? and Enity id = ? UNION SELECT Entity id FROM Actor Where Entity id = ?";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomEvent\t\tEvent id\t\tDateDebut\t\tDateFin\n");
		while(rst.next()) {
		   System.out.print("\t\t\t"+rst.getString(1));
		   System.out.print("\t\t\t"+rst.getLong(2));
		   System.out.print("\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t"+rst.getString(4));
		   System.out.println();
		}
		   rst.close();
		   preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event found!")).build();
    }
    @Override
    public Response findEventsByName( @NotNull String name, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomEvent, Event id, DateDebut, DateFin FROM Event WHERE NomEvent = ?";
    	try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomEvent\t\tEvent id\t\tDateDebut\t\tDateFin\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getLong(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getString(4));
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
    public Response getEventById(Long eventId, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT NomEvent, Event id, DateDebut, DateFin FROM Event WHERE Event id = ?";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomEvent\t\tEntity id\t\tDateDebut\t\tDateFin\n");
		while(rst.next()) {
		   System.out.print("\t\t\t"+rst.getString(1));
		   System.out.print("\t\t\t"+rst.getLong(2));
		   System.out.print("\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t"+rst.getString(4));
		   System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event found!")).build();
    }

    @Override
    public Response updateEventWithForm(Long eventId, String name, String status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updateevent(Event events, SecurityContext securityContext) throws NotFoundException {
    	String query = "UPDATE Event SET NomEvent = ?, DateDebut = ?, DateFin = ? where NomEvent = ? and Event id = ?";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
	    preparedStmt.setString(1, events.getName());
	    preparedStmt.setString(2, events.getStartdate());
	    preparedStmt.setString(2, events.getEnddate());
	    preparedStmt.executeUpdate();
	    preparedStmt.close();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event updated!")).build();
    }
}
