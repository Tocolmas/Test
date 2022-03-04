package io.swagger.api.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Event;
import io.swagger.model.Inventor;

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
public class EventApiServiceImpl extends EventApiService {
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
    public Response addevent(Event events, SecurityContext securityContext) throws NotFoundException {
    	String query = "INSERT INTO Event(Name, EventId, Startdate, Enddate, Status) VALUES(?,?,?,?,?)";
    	Connection conn = ConnectionManager.getConnection();
    	long id=ConnectionManager.createId("Event");
        events.setId(id);
        assert events.getName() == null;
        assert events.getId() == null;
        assert events.getStartdate() == null;
        assert events.getEnddate() == null;
        assert events.getStatus() == null;
	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, events.getName());
	        pstmt.setLong(2, events.getId());
	        pstmt.setString(3, events.getStartdate());
	        pstmt.setString(4, events.getEnddate());
	        pstmt.executeUpdate();
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
	    }
	   	assert events.getName() != null && !events.getName().trim().isEmpty();
	    assert events.getStartdate() != null && !events.getStartdate().trim().isEmpty();
	   	checkValidDate(events.getStartdate());
		checkValidDate(events.getEnddate());
		return Response.ok().entity(events).build();
    }

    @Override
    public Response deleteEvent(Long eventId, String apiKey, SecurityContext securityContext) throws NotFoundException {
       	String query = "DELETE FROM Event WHERE EventId = ?";
       	assert eventId != null;
       	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
  	        pstmt.setLong(1, eventId);
  	        pstmt.executeUpdate();
  	        pstmt.close();
  	    } catch (SQLException e) {
  	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
  	    }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event deleted!")).build();
    }
    @Override
    public Response eventfindByDate( String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EventId, Startdate, Enddate FROM Event WHERE Startdate = ?";
		Event eve = new Event();
		assert date != null;
		checkValidDate(date);
		Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    		preparedStmt.setString(1, date);
			ResultSet rst = preparedStmt.executeQuery();
			//System.out.println("tName\t\tEventId\t\tStartdate\t\tEnddate\n");
			rst.next();
			eve.setName(rst.getString(1));
			eve.setId(rst.getLong(2));
			eve.setStartdate(rst.getString(3));
			eve.setEnddate(rst.getString(4));

		    /*System.out.print("\t\t\t"+rst.getString(1));
		    System.out.print("\t\t\t"+rst.getLong(2));
		    System.out.print("\t\t\t"+rst.getString(3));
		    System.out.print("\t\t\t"+rst.getString(4));
		    System.out.println();*/

			}catch (SQLException e) {
		        e.printStackTrace();
		        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
	            builder.entity(ConnectionManager.buildException(e));
	            return builder.build();
			}
    		assert date == eve.getStartdate();
	    	return Response.ok(eve).build();
	    }
    @Override
    public Response findEventsByInvention( @NotNull List<String> event, SecurityContext securityContext) throws NotFoundException {
    	String query = "(SELECT * FROM Event UNION SELECT * FROM Participation) AS EP UNION SELECT * FROM Invention WHERE EntityId.EP = EntityId.Invention";
    	Event eve = new Event();
    	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		//System.out.println("tName\t\tEventId\t\tStartdate\t\tEnddate\n");
		rst.next();
		eve.setName(rst.getString(1));
		eve.setId(rst.getLong(2));
		eve.setStartdate(rst.getString(3));
		eve.setEnddate(rst.getString(4));

	    /*System.out.print("\t\t\t"+rst.getString(1));
	    System.out.print("\t\t\t"+rst.getLong(2));
	    System.out.print("\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t"+rst.getString(4));
	    System.out.println();*/

		}catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
		}
    	return Response.ok(eve).build();
	}
    @Override
    public Response findEventsByInventor( @NotNull List<String> event, SecurityContext securityContext) throws NotFoundException {
    	String query = "(SELECT * FROM Event UNION SELECT * FROM Participation) AS PE UNION SELECT * FROM Inventor WHERE EntityId.EP = EntityId.Inventor";
    	Event eve = new Event();
    	Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		//System.out.println("tName\t\tEventId\t\tStartdate\t\tEnddate\n");
		rst.next();
		eve.setName(rst.getString(1));
		eve.setId(rst.getLong(2));
		eve.setStartdate(rst.getString(3));
		eve.setEnddate(rst.getString(4));

	    /*System.out.print("\t\t\t"+rst.getString(1));
	    System.out.print("\t\t\t"+rst.getLong(2));
	    System.out.print("\t\t\t"+rst.getString(3));
	    System.out.print("\t\t\t"+rst.getString(4));
	    System.out.println();*/

		}catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
		}
		return Response.ok(eve).build();
    }
    @Override
    public Response findEventsByName( @NotNull String name, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EventId, Startdate, Enddate FROM Event WHERE Name = ?";
    	Event eve = new Event();
    	Connection conn = ConnectionManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    		preparedStmt.setString(1, name);
			ResultSet rst = preparedStmt.executeQuery();
			//System.out.println("tName\t\tEventId\t\tStartdate\t\tEnddate\n");
			rst.next();
			eve.setName(rst.getString(1));
			eve.setId(rst.getLong(2));
			eve.setStartdate(rst.getString(3));
			eve.setEnddate(rst.getString(4));

		    /*System.out.print("\t\t\t"+rst.getString(1));
		    System.out.print("\t\t\t"+rst.getLong(2));
		    System.out.print("\t\t\t"+rst.getString(3));
		    System.out.print("\t\t\t"+rst.getString(4));
		    System.out.println();*/

			}catch (SQLException e) {
		        e.printStackTrace();
		        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
	            builder.entity(ConnectionManager.buildException(e));
	            return builder.build();
			}
    		assert name == eve.getName();
	    	return Response.ok(eve).build();
	    }
    @Override
    public Response getEventById(Long eventId, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EventId, Startdate, Enddate FROM Event WHERE EventId = ?";
    	Event eve = new Event();
    	assert eventId != null;
    	Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
			preparedStmt.setLong(1, eventId);
			ResultSet rst = preparedStmt.executeQuery();
			//System.out.println("tName\t\tEventId\t\tStartdate\t\tEnddate\n");
			rst.next();
			eve.setName(rst.getString(1));
			eve.setId(rst.getLong(2));
			eve.setStartdate(rst.getString(3));
			eve.setEnddate(rst.getString(4));

		    /*System.out.print("\t\t\t"+rst.getString(1));
		    System.out.print("\t\t\t"+rst.getLong(2));
		    System.out.print("\t\t\t"+rst.getString(3));
		    System.out.print("\t\t\t"+rst.getString(4));
		    System.out.println();*/

			}catch (SQLException e) {
		        e.printStackTrace();
		        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
	            builder.entity(ConnectionManager.buildException(e));
	            return builder.build();
			}
		    assert eventId == eve.getId();
			return Response.ok(eve).build();
	    }
    @Override
    public Response updateEventWithForm(Long eventId, String name, String status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updateevent(Event events, SecurityContext securityContext) throws NotFoundException {
    	String query = "UPDATE Event SET Name = ?, Startdate = ?, Enddate = ?, Status = ? WHERE EventId = ?";
    	assert events.getId() != null;
    	Connection conn = ConnectionManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
	    preparedStmt.setString(1, events.getName());
	    preparedStmt.setString(2, events.getStartdate());
	    preparedStmt.setString(3, events.getEnddate());
	    preparedStmt.setString(4, events.getStatus());
	    preparedStmt.executeUpdate();

		}catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(ConnectionManager.buildException(e));
            return builder.build();
		}
		assert events.getName() != null;
		assert events.getStartdate() != null;
	   	checkValidDate(events.getStartdate());
		checkValidDate(events.getEnddate());
		return Response.ok().entity(events).build();
    }
}
