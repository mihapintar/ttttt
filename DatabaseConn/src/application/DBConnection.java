package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3316/world", "root", "Admin9660");
		
		
		
		System.out.println("Connected to DB");
		
		
	}
	
	
}
