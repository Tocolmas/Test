import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeleteInventions
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

	public void delete() {
      String query = "delete NomInventions from Invention where Entity id = ?";

      try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, 1);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
}
