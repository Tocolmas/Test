import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectInv
{
	private Connection connect() {
	    String url = "jdbc:sqlite:c://sqlite/db/projet.db";
	    Connection conn = null;
	    try {
	        conn = DriverManager.getConnection(url);
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return conn;
	}

	public void select() {
		String query = "select name, date, date2, nationalite, Entity id, firstname from Actor";

		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("Id\t\tName\t\tAge\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t"+rst.getString(2));
		   System.out.print("\t\t"+rst.getString(3));
		   System.out.print("\t\t"+rst.getString(4));
		   System.out.print("\t\t"+rst.getInt(5));
		   System.out.print("\t\t"+rst.getString(6));
		   System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
	}
}
