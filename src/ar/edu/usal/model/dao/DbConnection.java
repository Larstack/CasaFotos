package ar.edu.usal.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	private static DbConnection dbConnectionInstance = null;
	
	private String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String dbServer = "jdbc:sqlserver://localhost:1433;databaseName=TP_CasaFotos;integratedSecurity=true;";
	
	private Connection connection;
	
	private DbConnection(){
		
		try {
			
			Class.forName(this.dbDriver);
			this.connection = DriverManager.getConnection(dbServer);
		
		} catch (ClassNotFoundException e) {
			
			System.out.println("ERROR DE CONEXION A LA BASE DE DATOS.");

		} catch (SQLException e) {

			System.out.println("SQL ERROR: " + e.getMessage());
		}
	}

	public static DbConnection getInstance(){
		
		if(dbConnectionInstance==null){
			
			dbConnectionInstance = new DbConnection();
		}
		
		return dbConnectionInstance;
	}

	public Connection getConnection() {
		return this.connection;
	}

}
