import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateInventions
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

	public void update() {
		String query = "update Invention set Finie = ? where NomInventions = ?"; /*0 si l'invention est terminée, 1 si elle continue d'évoluer*/

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(query)){
	    pstmt.setInt   (1, 1);
	    pstmt.setString(2, "Windows");
	    pstmt.executeUpdate();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
	}
}
