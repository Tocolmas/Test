import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertInv {

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

	public void insert(String name, String date, String date2, String nationalite, String firstname) {
	    String query = "INSERT INTO Actors(name, date, date2, nationalite, firstname) VALUES(?,?,?,?,?)";

	    try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, name);
	        pstmt.setString(2, date);
	        pstmt.setString(2, date2);
	        pstmt.setString(2, nationalite);
	        pstmt.setString(2, firstname);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}

	public static void main(String[] args) {

	    InsertInv inv = new InsertInv();
	    // insert three new rows
	    inv.insert("Johns", "1935", "1985", "Americain", "Paul");
	    inv.insert("Sinclair", "1940", "2021", "Anglais", "Clive");
	    inv.insert("Jobs", "1955", "2011", "Americain", "Steve");
	    }

}
