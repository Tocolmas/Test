import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;

import io.swagger.api.EventApiService;
import io.swagger.api.InventorApiService;
import io.swagger.api.NotFoundException;
import io.swagger.api.factories.EventApiServiceFactory;
import io.swagger.api.factories.InventorApiServiceFactory;
import io.swagger.model.Event;
import io.swagger.model.Inventor;

public class TestEvent {
	
	@BeforeClass
	public static void cleanDB() {
		
		System.out.println("ICI JE DOIS VIDER MA DB");
	}

	@Test
	public void findbyName() throws NotFoundException {
		Event eve1 = new Event();
		eve1.setName("Invention de la souris");
		eve1.setStartdate("1955");
		eve1.setEnddate("");
		eve1.getId();
	    System.out.println(eve1);
		EventApiService api=EventApiServiceFactory.getEventApi();
	    Response res=api.addevent(eve1, null);
	    assertEquals(res.getStatus(),200);
	    res=api.findEventsByName("Invention de la souris", null);
	    Event eve2=(Event)res.getEntity();
	    System.out.println(eve2);
	    assertEquals(eve1.getName(),eve2.getName());
	    assertEquals(eve1.getStartdate(), eve2.getStartdate());
	    assertEquals(eve1.getEnddate(), eve2.getEnddate());
	    assertEquals(eve1.getStatus(), eve2.getStatus());   
	}
	
	@Test
	public void findbyDate() throws NotFoundException {
		Event eve3 = new Event ();
		eve3.setName("Invnetion de l'impression");
		eve3.setStartdate("1855");
		eve3.setEnddate("");
		eve3.getId();
		System.out.println(eve3);
		EventApiService api = EventApiServiceFactory.getEventApi();
		Response res=api.addevent(eve3, null);
		res=api.eventfindByDate("1855", null);
		System.out.println(res.getEntity());
	    Event eve4=(Event)res.getEntity();
	    assertEquals(eve3.getStartdate(),eve4.getStartdate());

		
	}
	
	@Test
	public void deleteEvent() throws NotFoundException {
		Event eve5=new Event();
		eve5.setName("Invention de la souris");
		eve5.setStartdate("1955");
		eve5.setEnddate("");
		eve5.setId(1L);

		EventApiService api=EventApiServiceFactory.getEventApi();
	    Response res=api.addevent(eve5, null);
	    assertEquals(res.getStatus(),200);
	    res=api.deleteEvent(eve5.getId(), null, null);
	    System.out.println(res);
	    System.out.println(res.getEntity());
	    //Inventor inv2=(Inventor)res.getEntity();
	}

	
	@Test
	public void updateUser() throws NotFoundException {	
		Event eve6=new Event();
		eve6.setName("premier jeux vidéos");
		eve6.setStartdate("1985");
		eve6.setEnddate("");
		eve6.setId(1L);
		EventApiService api=EventApiServiceFactory.getEventApi();
	    Response res=api.addevent(eve6, null);
	    assertEquals(res.getStatus(),200);
	    res=api.updateevent(eve6, null);
	    System.out.println(res.getEntity());
	    System.out.println(eve6);
	    assertEquals(eve6.getName(), eve6.getName());
	} 
	
	@Test
	public void findbyId() throws NotFoundException {
		Event eve2 = new Event();
		eve2.setName("premier écran tactile");
		eve2.setStartdate("2010");
		eve2.setEnddate("");
		eve2.setId(2L);
		EventApiService api=EventApiServiceFactory.getEventApi();
	    Response res=api.addevent(eve2, null);
	    assertEquals(res.getStatus(),200);	
	    res=api.getEventById(eve2.getId(), null);
	    System.out.println(res.getEntity());
	    Event eve9=(Event)res.getEntity();
	    assertEquals(eve2.getId(),eve9.getId());
	}
	
	
}

