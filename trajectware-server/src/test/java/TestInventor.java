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
		inv1.setBorndate("1955");
		inv1.setDeathdate("2021");
		inv1.setNationalite("Americain");
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
	    assertEquals(inv1.getName(),inv2.getName());
	    
	}
}
