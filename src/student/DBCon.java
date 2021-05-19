package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {

	static Connection conn;
	
	public static Connection connect() {
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
