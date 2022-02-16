package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import java.util.List;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2021-12-23T10:17:36.264Z")
public class EventApiServiceImpl extends EventApiService {
    @Override
    private Connection connect() {
	    String url = "jdbc:sqlite:c://sqlite/db/projet.db";
	    Connection conn = null;
	    try {
	        conn = DriverManager.getConnection(url);
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return conn;
	}
    public void insert(String NomEvent) {
		  String query = "INSERT INTO Event(NomEvent) VALUES(?)";

	    try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, NomEvent);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
    public Response addevent(Event events, SecurityContext securityContext) throws NotFoundException {
    	insert event = new insert();
	    event.insert("Invention de la souris");
      return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event added!")).build();
    }
    @Override
    public Response deleteEvent(Long eventId, String apiKey, SecurityContext securityContext) throws NotFoundException {
    	String query = "delete from Event where Entity id = ?";

        try (Connection conn = this.connect();
  	            PreparedStatement pstmt = conn.prepareStatement(query)) {
  	        pstmt.setString(1, "Invention de ...");
  	        pstmt.executeUpdate();
  	    } catch (SQLException e) {
  	        System.out.println(e.getMessage());
  	    }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event deleted!")).build();
    }
    @Override
    public Response eventfindByDate( String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "select DateDebut, DateFin from Actor"; /*Pas sur de pouvoir daire ça*/

  		try (Connection conn = this.connect();
  				PreparedStatement preparedStmt = conn.prepareStatement(query)){
  		ResultSet rst = preparedStmt.executeQuery();
  		System.out.println("tNomEvent\t\tEntity id\t\tDateDebut\t\tDateFin\n");
  		while(rst.next()) {
  		   System.out.print("\t\t\t"+rst.getString(1));
  		   System.out.print("\t\t\t"+rst.getInt(2));
  		   System.out.print("\t\t\t"+rst.getInt(3));
  		   System.out.print("\t\t\t"+rst.getInt(4));
  		   System.out.println();
  		}}catch (SQLException e) {
  	        System.out.println(e.getMessage());
  		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response findEventsByInvention( @NotNull List<String> event, SecurityContext securityContext) throws NotFoundException {
    	String query = "select Entity id from Event, Invention WHERE Entity id.Event = Entity id.Invention "; /*Pas sur de pouvoir daire ça*/

  		try (Connection conn = this.connect();
  				PreparedStatement preparedStmt = conn.prepareStatement(query)){
  		ResultSet rst = preparedStmt.executeQuery();
  		System.out.println("tNomEvent\t\tEntity id\t\tDateDebut\t\tDateFin\n");
  		while(rst.next()) {
  		   System.out.print("\t\t\t"+rst.getString(1));
  		   System.out.print("\t\t\t"+rst.getInt(2));
  		   System.out.print("\t\t\t"+rst.getInt(3));
  		   System.out.print("\t\t\t"+rst.getInt(4));
  		   System.out.println();
  		}}catch (SQLException e) {
  	        System.out.println(e.getMessage());
  		}
  		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event found!")).build();
	}

    @Override
    public Response findEventsByInventor( @NotNull List<String> event, SecurityContext securityContext) throws NotFoundException {
    	String query = "select Entity id from Event, Actor WHERE Entity id.Event = Entity id.Actor "; /*Pas sur de pouvoir daire ça*/

  		try (Connection conn = this.connect();
  				PreparedStatement preparedStmt = conn.prepareStatement(query)){
  		ResultSet rst = preparedStmt.executeQuery();
  		System.out.println("tNomEvent\t\tEntity id\t\tDateDebut\t\tDateFin\n");
  		while(rst.next()) {
  		   System.out.print("\t\t\t"+rst.getString(1));
  		   System.out.print("\t\t\t"+rst.getInt(2));
  		   System.out.print("\t\t\t"+rst.getInt(3));
  		   System.out.print("\t\t\t"+rst.getInt(4));
  		   System.out.println();
  		}}catch (SQLException e) {
  	        System.out.println(e.getMessage());

  		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event found!")).build();
	}

    @Override
    public Response findEventsByTags( @NotNull List<String> tags,  String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "select Entity id from Event, Feature WHERE Entity id.Event = Entity id.Feature"; /*Pas sur de pouvoir daire ça*/

  		try (Connection conn = this.connect();
  				PreparedStatement preparedStmt = conn.prepareStatement(query)){
  		ResultSet rst = preparedStmt.executeQuery();
  		System.out.println("tNomEvent\t\tEntity id\t\tDateDebut\t\tDateFin\n");
  		while(rst.next()) {
  		   System.out.print("\t\t\t"+rst.getString(1));
  		   System.out.print("\t\t\t"+rst.getInt(2));
  		   System.out.print("\t\t\t"+rst.getInt(3));
  		   System.out.print("\t\t\t"+rst.getInt(4));
  		   System.out.println();
  		}}catch (SQLException e) {
  	        System.out.println(e.getMessage());
  		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event found!")).build();
    }
    @Override
    public Response getEventById(Long eventId, SecurityContext securityContext) throws NotFoundException {
      String query = "select Entity id from Event"; /*Pas sur de pouvoir daire ça*/

  		try (Connection conn = this.connect();
  				PreparedStatement preparedStmt = conn.prepareStatement(query)){
  		ResultSet rst = preparedStmt.executeQuery();
  		System.out.println("tNomEvent\t\tEntity id\t\tDateDebut\t\tDateFin\n");
  		while(rst.next()) {
  		   System.out.print("\t\t\t"+rst.getString(1));
  		   System.out.print("\t\t\t"+rst.getInt(2));
  		   System.out.print("\t\t\t"+rst.getInt(3));
  		   System.out.print("\t\t\t"+rst.getInt(4));
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
    	String query = "update Event set DateFin = ? where NomEvent = ?";

  		try (Connection conn = this.connect();
  				PreparedStatement preparedStmt = conn.prepareStatement(query)){
  	    preparedStmt.setString(1, "2022");
  	    preparedStmt.setString(2, "Invention de ...");
  	    preparedStmt.executeUpdate();
  		}catch (SQLException e) {
  	        System.out.println(e.getMessage());
  		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event updated!")).build();
    }
}
