import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import io.swagger.api.InventorApiService;
import io.swagger.api.NotFoundException;
import io.swagger.api.factories.InventorApiServiceFactory;
import io.swagger.model.Inventor;

public class TestInventor {

	@Test
	
	public void test1() throws NotFoundException {
		Inventor inv1=new Inventor();
		inv1.setName("Gates");
		inv1.setBirthdate("1955");
		inv1.setDeathdate("2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");

		InventorApiService api=InventorApiServiceFactory.getInventorApi();
	    Response res=api.addInventor(inv1, null);
	    assertEquals(res.getStatus(),200);
	    res=api.findInventorsByName("Gates", null);
	    
	    /*
    	ObjectMapper m = Json.mapper();
	    Schema p = m.readValue(res.get, Schema.class);*/
	    System.out.println(res.getEntity());
	    Inventor inv2=(Inventor)res.getEntity();
	    assertNotEquals(inv1.getName(),inv2.getName());
	    
	}
	
	@Test
	public void test2() throws NotFoundException {
		Inventor inv1 = new Inventor ();
		inv1.setName("Gates");
		inv1.setBirthdate("1955");
		inv1.setDeathdate("2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");
		
		InventorApiService api = InventorApiServiceFactory.getInventorApi();
		Response res=api.addInventor(inv1, null);
		res=api.inventorfindByDate("1955", null);
		
		System.out.println(res.getEntity());
	    Inventor inv2=(Inventor)res.getEntity();
	    assertNotEquals(inv1.getBirthdate(),inv2.getBirthdate());
		
	}
	
	@Test
	public void test3() throws NotFoundException {
		Inventor inv1=new Inventor();
		Inventor inv2=new Inventor();
		inv1.setName("Gates");
		inv1.setBirthdate("1955");
		inv1.setDeathdate("2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");
		inv2.setName(null);
		inv2.setBirthdate(null);
		inv2.setDeathdate(null);
		inv2.setId(1L);
		inv2.setFirstname(null);
		inv2.setStatus(null);

		InventorApiService api=InventorApiServiceFactory.getInventorApi();
	    Response res=api.addInventor(inv1, null);
	    assertEquals(res.getStatus(),200);
	    res=api.deleteInventor(1L, null, null);
	    
	    System.out.println(res.getEntity());
	    assertEquals(inv1.getId(), inv2.getId());
	}
	
	@Test
	public void test4() throws NotFoundException {
		Inventor inv1=new Inventor();

		inv1.setName("Gates");
		inv1.setBirthdate("1955");
		inv1.setDeathdate("2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");


		InventorApiService api=InventorApiServiceFactory.getInventorApi();
	    Response res=api.addInventor(inv1, null);
	    assertEquals(res.getStatus(),200);
	    res=api.updateInventor(inv1, null);
	    inv1.setName("Gate");
		inv1.setBirthdate("1965");
		inv1.setDeathdate("2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");
	    System.out.println(res.getEntity());
	    
	    assertEquals(inv1.getName(), inv1.getName());
		} 

	}

