package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs {

	Connection dbconnection;
	
	public Connection getConnection() {
		
		String connectionString = "jdbc:mysql://"+ Configs.dbhost +":"+ Configs.dbport +"/"+Configs.dbname+"?autoReconnect=true&useSSL=false";
		System.out.println("connectionString:: "+ connectionString);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dbconnection = DriverManager.getConnection(connectionString, Configs.dbuser, Configs.dbpass);
			System.out.println("Connected to DB");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return dbconnection;
	}
	
	
}
