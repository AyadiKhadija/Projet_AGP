package persistence.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;

class JdbcConnection {

	
	private static String host = "localhost:8889";
	private static String base = "Canarie";
	private static String user = "root";
	private static String password = "root";
	private static String url = "jdbc:mysql://" + host + "/" + base;
	
	/**
	 * Lazy singleton instance.
	 */
	private static Connection connection;


	public static Connection getConnection() {
		if (connection == null) {
			try {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				connection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				System.err.println("Connection failed : " + e.getMessage());
			}
		}
		return connection;
	}
}
