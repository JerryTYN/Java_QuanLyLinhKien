package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
	public static String userName = "sa";
	public static String password = "123456";
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QLLK";
		
		Connection conn = DriverManager.getConnection(url, userName, password);
		
		return conn;
	}
}
