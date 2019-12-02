package br.edu.univas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	private static final String USER = "postgres";
	private static final String PASSWORD = "admin";
	
	public static Connection getConnection() {
		
		String url = "jdbc:postgresql://localhost/banco_de_dados";
		
		Properties properties = new Properties();
		properties.setProperty("user", USER);
		properties.setProperty("password", PASSWORD);
		
		try {
			return DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 throw new RuntimeException(e);
		}
	}
	
}
