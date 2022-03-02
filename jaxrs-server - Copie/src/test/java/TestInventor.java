import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;

import io.swagger.api.InventorApiService;
import io.swagger.api.NotFoundException;
import io.swagger.api.factories.InventorApiServiceFactory;
import io.swagger.model.Inventor;

public class TestInventor {
	
	@BeforeClass
	public static void cleanDB() {
		System.out.println("ICI JE DOIS VIDER MA DB");
	}

	@Test
	public void test1() throws NotFoundException {
		Inventor inv1=new Inventor();
		inv1.setName("Gates");
		inv1.setBirthdate("1955");
		inv1.setDeathdate("2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");
		/*
		inv2.setName("Gate");
		inv2.setBirthdate("1955");
		inv2.setDeathdate("");
		inv2.setId(11L);
		inv2.setFirstname("Joe");
		inv2.setStatus("vivant");
*/
	    System.out.println(inv1);
	    
		InventorApiService api=InventorApiServiceFactory.getInventorApi();
	    Response res=api.addInventor(inv1, null);
	    assertEquals(res.getStatus(),200);
	    res=api.findInventorsByName("Gates", null);
	    
	    /*
    	ObjectMapper m = Json.mapper();
	    Schema p = m.readValue(res.get, Schema.class);*/
	    /*System.out.println(res.getEntity()); */
	    Inventor inv2=(Inventor)res.getEntity();
	    System.out.println(inv2);
	    
	    assertEquals(inv1.getName(),inv2.getName());
	    assertEquals(inv1.getBirthdate(), inv2.getBirthdate());
	    assertEquals(inv1.getDeathdate(), inv2.getDeathdate());
	    assertEquals(inv1.getId(), inv2.getId());
	    assertEquals(inv1.getFirstname(), inv2.getFirstname());
	    assertEquals(inv1.getStatus(), inv2.getStatus());
	    
	    
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
	    System.out.println(res.getEntity());
	    
	    assertEquals(inv1.getName(), inv1.getName());
	} 
	

	@Test
	public void testPhoto() throws NotFoundException, FileNotFoundException {
		Inventor inv1=new Inventor();
		inv1.setName("Gates");
		inv1.setBirthdate("1955");
		inv1.setDeathdate("2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");
		InventorApiService api=InventorApiServiceFactory.getInventorApi();
	    Response res=api.addInventor(inv1, null);
	    System.out.println(res.getEntity());
	    
	    File file=new File("testimg","Bill_Gates.jpg");
	    FileInputStream fin=new FileInputStream(file);
		api=InventorApiServiceFactory.getInventorApi();
	    res=api.uploadImage(inv1.getId(), null, fin, null, null);
	}

}

