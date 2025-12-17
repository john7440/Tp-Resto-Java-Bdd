package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static Connection connection;
	
	private static final String URL = "jdbc:mariadb://localhost:3308/restaurant_db";
	private static final String USER = "root";
	private static final String PASSWORD = "Bi3rs077ZBGSv4lM98w8";
	
	
	public static Connection getConnection() throws SQLException{
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (ClassNotFoundException e) {
				throw new SQLException("Maria DB non trouvé", e);
			}
		}
		
		return connection;
	}
	
	public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
