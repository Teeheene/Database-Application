package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	//adjust accordingly
	private static final String URL = "jdbc:mysql://localhost:3306/basketball_app";
	private static final String USER = "root";
	private static final String PASSWORD = "3612383";

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
}
