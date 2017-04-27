package com.cmsdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection(){
		Connection connection = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
/*			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ashis", "root");*/
			
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@orca.csc.ncsu.edu:1521/orcl.csc.ncsu.edu", "msachde", "200131144");

		} catch (SQLException e) {

			System.out.println("Connection Failed!");
	        e.printStackTrace();
	        return null;
		}
		catch (ClassNotFoundException e) {
		    System.out.println("Cannot find Oracle JDBC Driver!");
		    e.printStackTrace();
		    return null;
		}
		
		return connection;
	}
}
