package dataMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	//private static String DRIVER = "";
	private static String URL = "jdbc:derby:C:\\Users\\renat\\MyDB";
	private static String USER = "user";
	private static String PASS = "pass";
	
	public Connection getConnection() {
		
		try {
			System.out.println("Conexao estabelecida!");
			return DriverManager.getConnection(URL, USER, PASS);
		}catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
}
