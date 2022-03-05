package io.swagger.api.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;

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
	// verify that the format of the date is respected
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
    	// connection to the DB
    	Connection conn = DBManager.getConnection();
    	// call the function createId, to generate the Id
    	long id=DBManager.createId("Event");
        events.setId(id);
        // use of the preparedStatement to gather the information and send it into the DB
	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, events.getName());
	        pstmt.setLong(2, events.getId());
	        pstmt.setString(3, events.getStartdate());
	        pstmt.setString(4, events.getEnddate());
	        pstmt.setString(5, events.getStatus());
	        pstmt.executeUpdate();
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(DBManager.buildException(e));                        
            return builder.build();
	    }
	    // check that Name, Startdate are not null or empty
	   	assert events.getName() != null && !events.getName().trim().isEmpty();
	    assert events.getStartdate() != null && !events.getStartdate().trim().isEmpty();
	    // check that the date is written in the right format
	    checkValidDate(events.getStartdate()); 
		return Response.ok().entity(events).build();
    }

    @Override
    public Response deleteEvent(Long eventId, String apiKey, SecurityContext securityContext) throws NotFoundException {
       	String query = "DELETE FROM Event WHERE EventId = ?";
        // check that the Id is not null
       	assert eventId != null;
       	// connection to the DB
       	Connection conn = DBManager.getConnection();
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
  	        pstmt.setLong(1, eventId);
  	        pstmt.executeUpdate();
  	        pstmt.close();
  	    } catch (SQLException e) {
  	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(DBManager.buildException(e));                        
            return builder.build();
  	    }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "event deleted!")).build();
    }
    @Override
    public Response eventfindByDate( String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EventId, Startdate, Enddate FROM Event WHERE Startdate = ?";
		Event eve = new Event();
		// check that the date is not null and its format
		assert date != null;
		checkValidDate(date); 
		// connection to the DB
		Connection conn = DBManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    		preparedStmt.setString(1, date);
    		// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			eve.setName(rst.getString(1));
			eve.setId(rst.getLong(2));
			eve.setStartdate(rst.getString(3));
			eve.setEnddate(rst.getString(4));
			}catch (SQLException e) {
		        e.printStackTrace();
		        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
	            builder.entity(DBManager.buildException(e));                        
	            return builder.build();
			}
    		//check that the date given in argument is the same that the one we are looking for
    		assert date.equals(eve.getStartdate());
	    	return Response.ok(eve).build();
	    }
    @Override
    public Response findEventsByInvention( @NotNull List<String> invention, SecurityContext securityContext) throws NotFoundException {
    	// no need to verify that the List<String> is not null, because we have it verify in the parameters of the method 
    	String query = "(SELECT * FROM Event UNION SELECT * FROM Participation) AS EP UNION SELECT * FROM Invention WHERE EntityId.EP = EntityId.Invention";
    	Event eve = new Event();
    	// connection to the DB
    	Connection conn = DBManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    	// use of resultSet to gather information from the DB
		ResultSet rst = preparedStmt.executeQuery();
		rst.next();
		eve.setName(rst.getString(1));
		eve.setId(rst.getLong(2));
		eve.setStartdate(rst.getString(3));
		eve.setEnddate(rst.getString(4));
		}catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(DBManager.buildException(e));                        
            return builder.build();
		}
    	return Response.ok(eve).build();
	}
    @Override
    public Response findEventsByInventor( @NotNull List<String> inventor, SecurityContext securityContext) throws NotFoundException {
    	// no need to verify that the List<String> is not null, because we have it verify in the parameters of the method 
    	String query = "(SELECT * FROM Event UNION SELECT * FROM Participation) AS PE UNION SELECT * FROM Inventor WHERE EntityId.EP = EntityId.Inventor";
    	Event eve = new Event();
    	// connection to the DB
    	Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
		// use of resultSet to gather information from the DB
		ResultSet rst = preparedStmt.executeQuery();
		rst.next();
		eve.setName(rst.getString(1));
		eve.setId(rst.getLong(2));
		eve.setStartdate(rst.getString(3));
		eve.setEnddate(rst.getString(4));
		}catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(DBManager.buildException(e));                        
            return builder.build();
		}
		return Response.ok(eve).build();
    }
    @Override
    public Response findEventsByName( @NotNull String name, SecurityContext securityContext) throws NotFoundException {
    	// no need to verify that the name given in arugment is not null, because we have it verify in the parameters of the method 
    	String query = "SELECT Name, EventId, Startdate, Enddate FROM Event WHERE Name = ?";
    	Event eve = new Event();
    	// connection to the DB
    	Connection conn = DBManager.getConnection();
    	try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
    		preparedStmt.setString(1, name);
    		// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			eve.setName(rst.getString(1));
			eve.setId(rst.getLong(2));
			eve.setStartdate(rst.getString(3));
			eve.setEnddate(rst.getString(4));
			}catch (SQLException e) {
		        e.printStackTrace();
		        /*ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
	            builder.entity(ConnectionManager.buildException(e));                        
	            return builder.build();*/
			}
    		// check that the name given in argument is the same that the one we are looking for
    		assert name.equals(eve.getName());
	    	return Response.ok(eve).build();
	    }
    @Override
    public Response getEventById(Long eventId, SecurityContext securityContext) throws NotFoundException {
    	String query = "SELECT Name, EventId, Startdate, Enddate FROM Event WHERE EventId = ?";
    	Event eve = new Event();
    	//check that the id is not null
    	assert eventId != null;
    	// connection to the DB
    	Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
			preparedStmt.setLong(1, eventId);
			// use of resultSet to gather information from the DB
			ResultSet rst = preparedStmt.executeQuery();
			rst.next();
			eve.setName(rst.getString(1));
			eve.setId(rst.getLong(2));
			eve.setStartdate(rst.getString(3));
			eve.setEnddate(rst.getString(4));
			}catch (SQLException e) {
		        e.printStackTrace();
		        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
	            builder.entity(DBManager.buildException(e));                        
	            return builder.build();
			}
			// check that the id given in argument is the same that the one we are looking for
		    assert eventId == eve.getId();
			return Response.ok(eve).build();
	    }
    @Override
    public Response updateEventWithForm(Long eventId, String name, String status, SecurityContext securityContext) throws NotFoundException {
		String query = "UPDATE Event SET Name = ?, Status = ? where EntityId = ?";
		// check that the arguments of the method are not
    	assert eventId != null;
    	assert name != null;
    	assert status != null;
    	// connection to the DB
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
    	return Response.ok().entity(eventId).build();
    }
    @Override
    public Response updateevent(Event events, SecurityContext securityContext) throws NotFoundException {
    	String query = "UPDATE Event SET Name = ?, Startdate = ?, Enddate = ?, Status = ? WHERE EventId = ?";
    	// check that the Id given in argument is the same that the one we are looking for
    	assert events.getId() != null;
    	// connection to the DB
    	Connection conn = DBManager.getConnection();
		try (PreparedStatement preparedStmt = conn.prepareStatement(query)){
	    preparedStmt.setString(1, events.getName());
	    preparedStmt.setString(2, events.getStartdate());
	    preparedStmt.setString(3, events.getEnddate());
	    preparedStmt.setString(4, events.getStatus());
	    preparedStmt.executeUpdate();
	    
		}catch (SQLException e) {
	        e.printStackTrace();
	        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.entity(DBManager.buildException(e));                        
            return builder.build();
		}
		assert events.getName() != null;
		assert events.getStartdate() != null;
	   	checkValidDate(events.getStartdate()); 
		return Response.ok().entity(events).build();
    }
}
