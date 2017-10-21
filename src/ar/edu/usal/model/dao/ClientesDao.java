package ar.edu.usal.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import ar.edu.usal.model.dto.Clientes;
import ar.edu.usal.model.dto.Localidades;

public class ClientesDao {

private static ClientesDao clientesDaoInstance = null;
	
	private ArrayList<Clientes> clientesList;
	private Connection conn;
	
	private ClientesDao(){
		
		DbConnection dbConn = DbConnection.getInstance();
		this.conn = dbConn.getConnection();
		this.clientesList = new ArrayList<Clientes>();
		this.loadClientes();
	}

	public static ClientesDao getInstance(){
		
		if(clientesDaoInstance==null){
			
			clientesDaoInstance = new ClientesDao();
		}
		
		return clientesDaoInstance;
	}
	
	private void loadClientes() {

		try {
			String sql = "SELECT id,nombre_apellido,domicilio,mail FROM Clientes";
			
			Statement stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int id = rs.getInt("id");
				String nombre = rs.getString("nombre_apellido");
				String domicilio = rs.getString("domicilio");
				String mail = rs.getString("mail");
				
				this.clientesList.add(new Clientes(id, nombre, domicilio, mail));
			}
		}catch (Exception e) {

			System.out.println("Se ha verificado un error al cargar las localidades.");
		}
	}

	public ArrayList<Clientes> getClientesList() {
		return clientesList;
	}	
	
	public Clientes getClientesById(int id) {
		
		for (Iterator iterator = clientesList.iterator(); iterator.hasNext();) {
			
			Clientes clientes = (Clientes) iterator.next();
			
			if (clientes.getId() == id) {
				
				return clientes;
				
			} 
			
		}
		
		return null;
		
	}
		
}
