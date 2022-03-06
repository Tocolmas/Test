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
import io.swagger.api.UserApiService;
import io.swagger.api.factories.InventionApiServiceFactory;
import io.swagger.api.factories.InventorApiServiceFactory;
import io.swagger.api.factories.UserApiServiceFactory;
import io.swagger.api.impl.DBManager;
import io.swagger.model.Invention;
import io.swagger.model.Inventor;
import io.swagger.model.User;

public class TestUser {
	
	@BeforeClass
	public static void cleanDB() {
		String query = "DELETE FROM User";
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
		User usr1=new User();
		usr1.setUsername("Souris");
		usr1.getId();
		usr1.setFirstName("Lou");
		usr1.setLastName("Joey");
		usr1.setEmail("");
		usr1.setPhone("");
		usr1.setPassword("root");
	    //System.out.println(usr1);
	    
		UserApiService api=UserApiServiceFactory.getUserApi();
	    Response res=api.adduser(usr1, null);
	    assertEquals(res.getStatus(),200);
	    System.out.println("ok");
	    res=api.getUserByName("Souris", null);
	    System.out.println(res.getEntity());
	    User usr2=(User)res.getEntity();
	    assertEquals(usr1.getUsername(),usr2.getUsername());
	    //assertEquals(usr1.getId(), usr2.getId());
	    assertEquals(usr1.getFirstName(), usr2.getFirstName());
	    assertEquals(usr1.getLastName(), usr2.getLastName());
	}
	
	@Test
	public void deleteUser() throws NotFoundException {
		User usr5=new User();
		usr5.setUsername("Sou");
		usr5.getId();
		usr5.setFirstName("Lop");
		usr5.setLastName("oeys");
		usr5.setEmail("");
		usr5.setPhone("");
		usr5.setPassword("root");
	    //System.out.println(usr5);
		UserApiService api=UserApiServiceFactory.getUserApi();
	    Response res=api.adduser(usr5, null);
	    assertEquals(res.getStatus(),200);
	    res=api.deleteUser("Sou", null);
	    System.out.println(res);
	    System.out.println(res.getEntity());
	    System.out.println("User deleted");
	}

	@Test
	public void updateUser() throws NotFoundException {
		User usr6=new User();
		usr6.setUsername("Soup");
		usr6.getId();
		usr6.setFirstName("oup");
		usr6.setLastName("Joys");
		usr6.setEmail("");
		usr6.setPhone("");
		usr6.setPassword("root");
	    //System.out.println(usr6);
		UserApiService api=UserApiServiceFactory.getUserApi();
	    Response res=api.adduser(usr6, null);
	    assertEquals(res.getStatus(),200);
	    res=api.updateUser("Sou", usr6, null);
	    System.out.println(res.getEntity());
	    System.out.println(usr6);
	    System.out.println("updated");
	} 
	
	@Test
	public void add() throws NotFoundException {
		User usr8=new User();
		usr8.setUsername("So");
		usr8.getId();
		usr8.setFirstName("Louk");
		usr8.setLastName("Joeys");
		usr8.setEmail("");
		usr8.setPhone("");
		usr8.setPassword("root");
	    //System.out.println(usr8);
		UserApiService api=UserApiServiceFactory.getUserApi();
	    Response res=api.adduser(usr8, null);
	    assertEquals(res.getStatus(),200);
	    System.out.println(res.getEntity());
	    System.out.println(usr8);
	}
	
	
}

