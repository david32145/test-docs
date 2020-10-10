package testdocs.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCPostgresConnection {
	private static Connection connection;
	
	private JDBCPostgresConnection() {}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(connection == null || connection.isClosed()) {
			connection = createConnection();
		}
		return connection;
	}

	private static Connection createConnection() throws ClassNotFoundException, SQLException {
		String driver = "org.postgresql.Driver";
        String user = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://localhost:5432/test_docs";
        Class.forName(driver);
        return (Connection) DriverManager.getConnection(url, user, password);
	}
}
