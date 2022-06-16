package br.com.healthtrack.singleton;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	private static DBManager instance;
	
	private DBManager() {}
	
	public static DBManager getInstance() {
		if(instance == null) {
			instance = new DBManager();
		}
		
		return instance;
	}
	
	public Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(
				"jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", 
				"USER",
				"PASSWORD"
			);

		} catch (Exception e) {
			System.out.println("Não foi possível conectar ao banco de dados.");
			e.printStackTrace();
		}

		return connection;
	}
}
