import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.api.InventionApiService;
import io.swagger.api.InventorApiService;
import io.swagger.api.NotFoundException;
import io.swagger.api.factories.InventionApiServiceFactory;
import io.swagger.api.factories.InventorApiServiceFactory;
import io.swagger.model.Invention;
import io.swagger.model.Inventor;
import io.swagger.util.Json;

public class TestInvention {

	@Test
	public void test1() throws NotFoundException {
		Inventor inv1=new Inventor();
		inv1.setName("Gates");
		inv1.setBorndate("1955");
		inv1.setDeathdate("2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");

		InventionApiService api=InventionApiServiceFactory.getInventionApi();
		Invention inv11 = new Invention();
	    Response res=api.addInvention(inv11, null);
	    assertEquals(res.getStatus(),200);
	    res=api.findInventionsByName("Gates", null);
	    
	    /*
    	ObjectMapper m = Json.mapper();
	    Schema p = m.readValue(res.get, Schema.class);*/
	    System.out.println(res.getEntity());
	    Inventor inv2=(Inventor)res.getEntity();
	    assertNotEquals(inv11.getName(),inv2.getName());
	    
	}
	
	@Test
	public void test2() throws NotFoundException {
		Inventor inv1 = new Inventor ();
		inv1.setName("Gates");
		inv1.setBorndate("1955");
		inv1.setDeathdate("2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");
		
		InventionApiService api = InventionApiServiceFactory.getInventionApi();
		Invention inv11 = new Invention();
		Response res=api.addInvention(inv11, null);
		res=api.findByDate("1955", null);
		
		System.out.println(res.getEntity());
	    Invention inv2=(Invention)res.getEntity();
	    assertNotEquals(inv11.getStartdate(),inv2.getStartdate());
		
	}
	
	@Test
	public void test3() throws NotFoundException {
		Inventor inv1=new Inventor();
		Inventor inv2=new Inventor();
		inv1.setName("Gates");
		inv1.setBorndate("1955");
		inv1.setDeathdate("2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");
		inv2.setName(null);
		inv2.setBorndate(null);
		inv2.setDeathdate(null);
		inv2.setId(1L);
		inv2.setFirstname(null);
		inv2.setStatus(null);

		InventionApiService api=InventionApiServiceFactory.getInventionApi();
		Invention inv11 = new Invention();
	    Response res=api.addInvention(inv11, null);
	    assertEquals(res.getStatus(),200);
	    res=api.deleteInvention(1L, null, null);
	    
	    System.out.println(res.getEntity());
	    assertEquals(inv11.getId(), inv2.getId());
	}
	
	@Test
	public void test4() throws NotFoundException {
		Inventor inv1=new Inventor();

		inv1.setName("Gates");
		inv1.setBorndate("1955");
		inv1.setDeathdate("2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");


		InventionApiService api=InventionApiServiceFactory.getInventionApi();
		Invention inv11 = new Invention();
	    Response res=api.addInvention(inv11, null);
	    assertEquals(res.getStatus(),200);
	    res=api.updateInvention(inv11, null);
	    inv1.setName("Gate");
		inv1.setBorndate("1965");
		inv1.setDeathdate("2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");
	    System.out.println(res.getEntity());
	    
	    assertEquals(inv11.getName(), inv11.getName());
	}
} 
