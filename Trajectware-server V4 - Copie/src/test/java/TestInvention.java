import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;

import io.swagger.api.InventionApiService;
import io.swagger.api.InventorApiService;
import io.swagger.api.NotFoundException;
import io.swagger.api.factories.InventionApiServiceFactory;
import io.swagger.api.factories.InventorApiServiceFactory;
import io.swagger.api.impl.DBManager;
import io.swagger.model.Invention;
import io.swagger.model.Inventor;

public class TestInvention {
	
	@BeforeClass
	public static void cleanDB() {
		String query = "DELETE FROM Invention";
	    Connection conn = DBManager.getConnection();
	    
		try {PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
	  	    pstmt.close();   
	  	    System.out.println("Nettoyage ok");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	                         
	    
	}

	@Test
	public void findbyName() throws NotFoundException {
		Invention inv1=new Invention();
		inv1.setName("Souris");
		inv1.getId();
		inv1.setStartdate("10/02/1955");
		inv1.setFinsihdate("");
		inv1.setStatus("en cours");
	    System.out.println(inv1);
	    
		InventionApiService api=InventionApiServiceFactory.getInventionApi();
	    Response res=api.addInvention(inv1, null);
	    assertEquals(res.getStatus(),200);
	    res=api.findInventionsByName("Souris", null);
	    System.out.println(res.getEntity());
	    Invention inv2=(Invention)res.getEntity();
	    assertEquals(inv1.getName(),inv2.getName());
	    assertEquals(inv1.getStartdate(), inv2.getStartdate());
	    assertEquals(inv1.getFinsihdate(), inv2.getFinsihdate());
	    assertEquals(inv1.getStatus(), inv2.getStatus());
	}
	
	@Test
	public void findbyDate() throws NotFoundException {
		Invention inv3 = new Invention();
		inv3.setName("USB");
		inv3.getId();
		inv3.setStartdate("10/02/1985");
		inv3.setFinsihdate("2021");
		inv3.setStatus("en cours");
		System.out.println(inv3);
		InventionApiService api = InventionApiServiceFactory.getInventionApi();
		Response res=api.addInvention(inv3, null);
		assertEquals(res.getStatus(),200);
		res=api.findByDate("10/02/1985", null);
		System.out.println(res.getEntity());
	    Invention inv4=(Invention)res.getEntity();
	    assertEquals(inv3.getStartdate(),inv4.getStartdate());
	}
	
	@Test
	public void deleteInvention() throws NotFoundException {
		Invention inv5=new Invention();
		inv5.setName("Ordi");
		inv5.setStartdate("10/04/1975");
		inv5.setFinsihdate("");
		inv5.setId(1L);
		inv5.setStatus("fini");

		InventionApiService api=InventionApiServiceFactory.getInventionApi();
	    Response res=api.addInvention(inv5, null);
	    assertEquals(res.getStatus(),200);
	    res=api.deleteInvention(inv5.getId(), null, null);
	    System.out.println(res);
	    System.out.println(res.getEntity());
	    //Inventor inv2=(Inventor)res.getEntity();
	}

	@Test
	public void updateInvention() throws NotFoundException {
		
		Invention inv1=new Invention();
		inv1.setName("invention du CD");
		inv1.setStartdate("01/03/1985");
		inv1.setFinsihdate("");
		inv1.setId(1L);
		inv1.setStatus("vivant");
		InventionApiService api=InventionApiServiceFactory.getInventionApi();
	    Response res=api.addInvention(inv1, null);
	    assertEquals(res.getStatus(),200);
	    res=api.updateInvention(inv1, null);
	    System.out.println(res.getEntity());
	    System.out.println(inv1);
	    assertEquals(inv1.getName(), inv1.getName());
	} 

	@Test
	public void testPhoto() throws NotFoundException, FileNotFoundException {
		Invention inv5=new Invention();
		inv5.setName("invention de la clé usb");
		inv5.setStartdate("10/08/1955");
		inv5.setFinsihdate("10/08/2021");
		inv5.setId(1L);
		inv5.setStatus("mort");
		InventionApiService api=InventionApiServiceFactory.getInventionApi();
	    Response res=api.addInvention(inv5, null);
	    System.out.println(res.getEntity());
	    
	    File file=new File("testimg","Bill_Gates.jpg");
	    FileInputStream fin=new FileInputStream(file);
		api=InventionApiServiceFactory.getInventionApi();
	    res=api.uploadFile(inv5.getId(), null, fin, null, null);
	}
	
	@Test
	public void findbyStatus() throws NotFoundException {
		Invention inv6=new Invention();
		inv6.setName("Puce");
		inv6.setStartdate("15/09/1955");
		inv6.setFinsihdate("");
		inv6.setId(inv6.getId());
		inv6.setStatus("en cours");
		
		InventionApiService api=InventionApiServiceFactory.getInventionApi();
	    Response res=api.addInvention(inv6, null);
	    assertEquals(res.getStatus(),200);	
	    res=api.findInventionsByStatus("en cours", null);
	    System.out.println(res.getEntity());
	    Invention inv8=(Invention)res.getEntity();
	    assertEquals(inv6.getStatus(), inv8.getStatus());
	}
	@Test
	public void findbyId() throws NotFoundException {
		Invention inv7=new Invention();
		inv7.setName("Jeux");
		inv7.setStartdate("08/09/1975");
		inv7.setFinsihdate("");
		inv7.setId(2L);
		inv7.setStatus("en cours");
		InventionApiService api=InventionApiServiceFactory.getInventionApi();
	    Response res=api.addInvention(inv7, null);
	    assertEquals(res.getStatus(),200);	
	    res=api.getInventionById(inv7.getId(), null);
	    System.out.println(res.getEntity());
	    Invention inv8=(Invention)res.getEntity();
	    assertEquals(inv7.getId(), inv8.getId());
	}
	
	@Test
	public void add() throws NotFoundException {
		Invention inv7=new Invention();
		inv7.setName("invention de la cassette audio");
		inv7.setStartdate("10/06/1940");
		inv7.setFinsihdate("2015");
		inv7.setId(3L);
		inv7.setStatus("mort");
		InventionApiService api=InventionApiServiceFactory.getInventionApi();
	    Response res=api.addInvention(inv7, null);
	    assertEquals(res.getStatus(),200);
	    res=api.addInvention(inv7, null);
	    System.out.println(res.getEntity());
	    System.out.println(inv7);
	}
	
}

