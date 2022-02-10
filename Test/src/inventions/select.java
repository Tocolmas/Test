import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectInventions
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
		String query = "select NomInventions, Entity id, Finie from Invention";

		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rlt = preparedStmt.executeQuery();
		System.out.println("Id\t\tName\t\tAge\n");
		while(rlt.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t"+rlt.getString(2));
		   System.out.print("\t\t"+rlt.getString(3));
		   System.out.print("\t\t"+rlt.getString(4));
		   System.out.print("\t\t"+rlt.getInt(5));
		   System.out.print("\t\t"+rlt.getString(6));
		   System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
	}
}
