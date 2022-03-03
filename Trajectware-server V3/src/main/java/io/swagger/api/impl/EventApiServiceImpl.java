package io.swagger.api.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Event;
import io.swagger.model.Inventor;

import java.util.List;
import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-02-21T11:04:43.972Z")
public class EventApiServiceImpl extends EventApiService {
    @Override
    public Response addevent(Event events, SecurityContext securityContext) throws NotFoundException {
    	String query = "INSERT INTO Event(Name, Startdate, Enddate) VALUES(?,?,?)";
    	Connection conn = ConnectionManager.getConnection();
	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, events.getName());
	        pstmt.setString(2, events.getStartdate());
	        pstmt.setString(3, events.getEnddate());
	        pstmt.executeUpdate();
	        //pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "new event added")).build();
    }

    @Override
    public Response deleteEvent(Long eventId, String apiKey, SecurityContext securityContext) throws NotFoundException {
       	String query = "DELETE FROM Event WHERE EventId = ?";
       	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
  	        pstmt.setLong(1, eventId);
  	        pstmt.executeUpdate();
  	        //pstmt.close();
  	    } catch (SQLException e) {
  	        e.printStackTrace();
  	    }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event deleted!")).build();
    }
    @Override 
    public Response eventfindByDate( String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EventId, Startdate, Enddate FROM Event WHERE Startdate = ?"; 
		Event eve = new Event();
		Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    		preparedStmt.setString(1, date);
			ResultSet rst = preparedStmt.executeQuery();
			System.out.println("tName\t\tEventId\t\tStartdate\t\tEnddate\n");
			rst.next();
			eve.setName(rst.getString(1));
			eve.setId(rst.getLong(2));
			eve.setStartdate(rst.getString(3));
			eve.setEnddate(rst.getString(4));
					
		    System.out.print("\t\t\t"+rst.getString(1));
		    System.out.print("\t\t\t"+rst.getLong(2));
		    System.out.print("\t\t\t"+rst.getString(3));
		    System.out.print("\t\t\t"+rst.getString(4));
		    System.out.println();
	
		    //rst.close();
		    //preparedStmt.close();
			}catch (SQLException e) {
		        System.out.println(e.getMessage());
			}
	    	return Response.ok(eve).build();
	    }
    @Override
    public Response findEventsByInvention( @NotNull List<String> event, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT * FROM (SELECT Name, EventId, Startdate, Enddate FROM Event WHERE EventId = ? UNION SELECT EventId, EntityId FROM Participation WHERE EventId = ? AND EntityId = ? UNION SELECT EntityId FROM Invention Where EntityId = ? ORDER BY EventId";
    	Event eve = new Event();
    	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tName\t\tEventId\t\tStartdate\t\tEnddate\n");
		rst.next();
		eve.setName(rst.getString(1));
		eve.setId(rst.getLong(2));
		eve.setStartdate(rst.getString(3));
		eve.setEnddate(rst.getString(4));
		
	    System.out.print("\t\t\t"+rst.getString(1));
	    System.out.print("\t\t\t"+rst.getLong(2));
	    System.out.print("\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t"+rst.getString(4));
	    System.out.println();
	
	    //rst.close();
	    //preparedStmt.close();
		}catch (SQLException e) {
	        e.printStackTrace();
		}
    	return Response.ok(eve).build();
	}
    @Override
    public Response findEventsByInventor( @NotNull List<String> event, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT * FROM (SELECT Name, EventId, Startdate, Enddate FROM Event WHERE EventId = ? UNION SELECT EventId, EntityId FROM Participation WHERE EventId = ? and EntityId = ? UNION SELECT EntityId FROM INVENTOR WHERE EntityId = ? ORDER BY EventId";
    	Event eve = new Event();
    	Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tName\t\tEventId\t\tStartdate\t\tEnddate\n");
		rst.next();
		eve.setName(rst.getString(1));
		eve.setId(rst.getLong(2));
		eve.setStartdate(rst.getString(3));
		eve.setEnddate(rst.getString(4));

	    System.out.print("\t\t\t"+rst.getString(1));
	    System.out.print("\t\t\t"+rst.getLong(2));
	    System.out.print("\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t"+rst.getString(4));
	    System.out.println();
	
//	    rst.close();
//	    preparedStmt.close();
		}catch (SQLException e) {
	        e.printStackTrace();
		}
		return Response.ok(eve).build();
    }
    @Override 
    public Response findEventsByName( @NotNull String name, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EventId, Startdebut, Enddate FROM Event WHERE Name = ?";
    	Event eve = new Event();
    	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    		preparedStmt.setString(1, name);
			ResultSet rst = preparedStmt.executeQuery();
			System.out.println("tName\t\tEventId\t\tStartdate\t\tEnddate\n");
			rst.next();
			eve.setName(rst.getString(1));
			eve.setId(rst.getLong(2));
			eve.setStartdate(rst.getString(3));
			eve.setEnddate(rst.getString(4));
		
		    System.out.print("\t\t\t"+rst.getString(1));
		    System.out.print("\t\t\t"+rst.getLong(2));
		    System.out.print("\t\t\t"+rst.getString(3));
		    System.out.print("\t\t\t"+rst.getString(4));
		    System.out.println();
		
		    //rst.close();
		    //preparedStmt.close();
			}catch (SQLException e) {
		        e.printStackTrace();
			}
	    	return Response.ok(eve).build();
	    }
    @Override
    public Response getEventById(Long eventId, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EventId, Startdate, Enddate FROM Event WHERE EventId = ?";
    	Event eve = new Event();
    	Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
			preparedStmt.setLong(1, eventId);
			ResultSet rst = preparedStmt.executeQuery();
			System.out.println("tName\t\tEventId\t\tStartdate\t\tEnddate\n");
			rst.next();
			eve.setName(rst.getString(1));
			eve.setId(rst.getLong(2));
			eve.setStartdate(rst.getString(3));
			eve.setEnddate(rst.getString(4));

		    System.out.print("\t\t\t"+rst.getString(1));
		    System.out.print("\t\t\t"+rst.getLong(2));
		    System.out.print("\t\t\t"+rst.getString(3));
		    System.out.print("\t\t\t"+rst.getString(4));
		    System.out.println();
		
		    //rst.close();
		    //preparedStmt.close();
			}catch (SQLException e) {
		        e.printStackTrace();
			}
			return Response.ok(eve).build();
	    }
    @Override
    public Response updateEventWithForm(Long eventId, String name, String status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updateevent(Event events, SecurityContext securityContext) throws NotFoundException {
    	String query = "UPDATE Event SET Name = ?, Startdate = ?, Enddate = ? WHERE EventId = ?"; 
		
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
	    preparedStmt.setString(1, events.getName());
	    preparedStmt.setString(2, events.getStartdate());
	    preparedStmt.setString(2, events.getEnddate());
	    preparedStmt.executeUpdate();
	    //preparedStmt.close();
		}catch (SQLException e) {
	        e.printStackTrace();
		}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event updated!")).build();
    }
}
