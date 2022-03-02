package io.swagger.api.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	public static final String DB_URL= "jdbc:sqlite:project.db"; // TODO mettre dans un fichier de config (clef/valeur)
	
	// singleton
    private static Connection conn;
	
    private static Connection createConnection() {
	    Connection conn = null;
	    try {
	        conn = DriverManager.getConnection(DB_URL);
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return conn;
	}
	
	public static Connection getConnection() {
		if (conn==null) conn=createConnection();
		return conn;
	}
}
