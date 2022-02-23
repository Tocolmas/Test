import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.api.InventorApiService;
import io.swagger.api.NotFoundException;
import io.swagger.api.factories.InventorApiServiceFactory;
import io.swagger.model.Inventor;
import io.swagger.util.Json;

public class TestInventor {

	@Test
	public void test1() throws NotFoundException {
		Inventor inv1=new Inventor();
		inv1.setName("Gates");
		inv1.setFirstname("Bill");

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
		inv1.setBorndate("1955");
		inv1.setDeathdate("2021");

		InventorApiService api = InventorApiServiceFactory.getInventorApi();
		Response res=api.addInventor(inv1, null);
		res=api.inventorfindByDate("1955", null);

		System.out.println(res.getEntity());
	    Inventor inv2=(Inventor)res.getEntity();
	    assertNotEquals(inv1.getBorndate(),inv2.getBorndate());

	}

	@Test
	public void test3() throws NotFoundException {

	}

}
