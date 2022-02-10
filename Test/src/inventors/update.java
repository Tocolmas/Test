import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UpdateInv
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
		String query = "update Actor set DateMort = ? where first_name = ?"; /*dans le cas ou l'inventeur n'est pas encore mort*/

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(query)){
	    pstmt.setInt   (1, 2022);
	    pstmt.setString(2, "Joe");
	    pstmt.executeUpdate();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
	}
}
