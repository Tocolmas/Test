import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.junit.BeforeClass;
import org.junit.Test;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.InventionApiService;
import io.swagger.api.InventorApiService;
import io.swagger.api.NotFoundException;
import io.swagger.api.factories.InventionApiServiceFactory;
import io.swagger.api.factories.InventorApiServiceFactory;
import io.swagger.api.impl.DBManager;
import io.swagger.model.Invention;
import io.swagger.model.Inventor;

public class TestInventor {

	@BeforeClass
	public static void cleanDB() {
		String query = "DELETE FROM Inventor";
		Connection conn = DBManager.getConnection();

		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
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
		Inventor inv15 = new Inventor();
		// long id = DBManager.createId("Inventor");
		inv15.setName("Gaes");
		inv15.setBirthdate("10/07/1959");
		inv15.setDeathdate("04/05/2021");
		inv15.getId();
		inv15.setFirstname("Bill");
		inv15.setStatus("mort");
		inv15.setNationalite("US");
		System.out.println(inv15);

		InventorApiService api = InventorApiServiceFactory.getInventorApi();
		Response res = api.addInventor(inv15, null);
		assertEquals(res.getStatus(), 200);
		res = api.findInventorsByName("Gaes", null);
		Inventor inv2 = (Inventor) res.getEntity();
		System.out.println(inv2);

		assertEquals(inv15.getName(), inv2.getName());
		assertEquals(inv15.getBirthdate(), inv2.getBirthdate());
		assertEquals(inv15.getDeathdate(), inv2.getDeathdate());
		assertEquals(inv15.getFirstname(), inv2.getFirstname());
		assertEquals(inv15.getStatus(), inv2.getStatus());
	}

	@Test
	public void findbyDate() throws NotFoundException {
		Inventor inv3 = new Inventor();
		inv3.setName("gomez");
		inv3.setBirthdate("23/05/1998");
		inv3.setDeathdate("07/05/2021");
		inv3.getId();
		inv3.setFirstname("James");
		inv3.setStatus("");
		System.out.println(inv3);
		InventorApiService api = InventorApiServiceFactory.getInventorApi();
		Response res = api.addInventor(inv3, null);
		assertEquals(res.getStatus(), 200);
		res = api.inventorfindByDate("23/05/1998", null);
		System.out.println(res.getEntity());
		Inventor inv4 = (Inventor) res.getEntity();
		assertEquals(inv3.getBirthdate(), inv4.getBirthdate());

	}

	@Test
	public void deleteInventor() throws NotFoundException {
		Inventor inv1 = new Inventor();
		inv1.setName("Gates");
		inv1.setBirthdate("01/02/1955");
		inv1.setDeathdate("02/01/2021");
		inv1.setId(1L);
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");

		InventorApiService api = InventorApiServiceFactory.getInventorApi();
		Response res = api.addInventor(inv1, null);
		assertEquals(res.getStatus(), 200);
		res = api.deleteInventor(inv1.getId(), null, null);
		System.out.println(res);
		System.out.println(res.getEntity());
		// Inventor inv2=(Inventor)res.getEntity();
	}

	@Test
	public void updateUser() throws NotFoundException {

		Inventor inv1 = new Inventor();

		inv1.setName("Jones");
		inv1.setBirthdate("15/05/1985");
		inv1.setDeathdate("");
		inv1.setId(1L);
		inv1.setFirstname("Tom");
		inv1.setStatus("vivant");

		InventorApiService api = InventorApiServiceFactory.getInventorApi();
		Response res = api.addInventor(inv1, null);
		assertEquals(res.getStatus(), 200);
		res = api.updateInventor(inv1, null);
		System.out.println(res.getEntity());
		System.out.println(inv1);
		assertEquals(inv1.getName(), inv1.getName());
	}

	@Test
	public void testPhoto() throws NotFoundException, FileNotFoundException {
		Inventor inv1 = new Inventor();
		inv1.setName("Gaps");
		inv1.setBirthdate("10/06/1955");
		inv1.setDeathdate("15/07/2021");
		inv1.getId();
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");
		InventorApiService api = InventorApiServiceFactory.getInventorApi();
		Response res = api.addInventor(inv1, null);
		System.out.println(res.getEntity());

		File file = new File("testimg", "Bill_Gates.jpg");
		FileInputStream fin = new FileInputStream(file);
		api = InventorApiServiceFactory.getInventorApi();
		res = api.uploadImage(inv1.getId(), null, fin, null, null);
		
	}

	@Test
	public void findbyStatus() throws NotFoundException {
		Inventor inv1 = new Inventor();
		inv1.setName("Ges");
		inv1.setBirthdate("10/01/1955");
		inv1.setDeathdate("15/08/2021");
		inv1.setId(inv1.getId());
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");

		InventorApiService api = InventorApiServiceFactory.getInventorApi();
		Response res = api.addInventor(inv1, null);
		assertEquals(res.getStatus(), 200);
		res = api.findInventorsByStatus("mort", null);
		System.out.println(res.getEntity());
	}

	@Test
	public void findbyId() throws NotFoundException {
		Inventor inv1 = new Inventor();
		inv1.setName("Gaks");
		inv1.setBirthdate("07/08/1955");
		inv1.setDeathdate("06/06/2021");
		inv1.getId();
		inv1.setFirstname("Bill");
		inv1.setStatus("mort");
		InventorApiService api = InventorApiServiceFactory.getInventorApi();
		Response res = api.addInventor(inv1, null);
		assertEquals(res.getStatus(), 200);
		res = api.getInventorById(inv1.getId(), null);
		System.out.println(res.getEntity());
	}

	@Test
	public void add() throws NotFoundException {
		Inventor inv7 = new Inventor();
		inv7.setName("Pauls");
		inv7.setBirthdate("14/07/1964");
		inv7.setDeathdate("");
		inv7.getId();
		inv7.setFirstname("Jeremy");
		inv7.setStatus("");
		InventorApiService api = InventorApiServiceFactory.getInventorApi();
		Response res = api.addInventor(inv7, null);
		assertEquals(res.getStatus(), 200);
		res = api.addInventor(inv7, null);
		System.out.println(res.getEntity());
		System.out.println(inv7);
	}

}
