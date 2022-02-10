import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertInvention {

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

	public void insert(NomInventions) {
	    String query = "INSERT INTO Invention(NomInventions) VALUES(?)";

	    try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, NomInventions);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}

	public static void main(String[] args) {

	    InsertInv inv = new InsertInv();
	    // insert three new rows
	    inv.insert("Mac");
	    inv.insert("Microsoft");
	    inv.insert("Atom");
	    }

}
