package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
	
static Connection conn;
	
	static {
		//step 1 of jdbc
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver Succefully Loaded....");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	static Connection obtainConnection() {
	// design pattern - singleton design pattern
		
		//step 2 establish connection
	
		String connectionUrl = "jdbc:postgresql://ip-172-31-28-50.ec2.internal:8888/project1";
		String userName = "postgres";
		String password = "mysecretpassword";
		
		
		if(conn == null) {
		try {
			conn = DriverManager.getConnection(connectionUrl, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return conn;
		
		}

	static void closeConnection() {
		// TODO Auto-generated method stub
		
	}

}
