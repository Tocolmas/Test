package io.swagger.api.impl;

import java.net.BindException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBManager {
	
	public static final String DB_URL= "jdbc:sqlite:project.db"; // TODO mettre dans un fichier de config (clef/valeur)
	public static Map<String,Long> map=new HashMap<String,Long>();
	
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
	
    // id management
    public static synchronized long createId(String table) {
        long id;
        if (map.containsKey(table)) {
            id=map.get(table);
            id++; // autoincrement
        } else {
            id=0;
        }
        map.put(table,id);
        return id;
    }

    protected static String buildException(Exception ex){                

        return "{\"message\": \"" + ex.getMessage() + "\"}";
    }
}
