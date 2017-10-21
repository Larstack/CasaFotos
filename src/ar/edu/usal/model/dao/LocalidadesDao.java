package ar.edu.usal.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import ar.edu.usal.model.dto.Localidades;

public class LocalidadesDao {

private static LocalidadesDao localidadesDaoInstance = null;
	
	private ArrayList<Localidades> localidadesList;
	private Connection conn;
	
	private LocalidadesDao(){
		
		DbConnection dbConn = DbConnection.getInstance();
		this.conn = dbConn.getConnection();
		this.localidadesList = new ArrayList<Localidades>();
		this.loadLocalidades();
	}

	public static LocalidadesDao getInstance(){
		
		if(localidadesDaoInstance==null){
			
			localidadesDaoInstance = new LocalidadesDao();
		}
		
		return localidadesDaoInstance;
	}
	
	private void loadLocalidades() {

		try {
			String sql = "SELECT id, localidad FROM Localidades";

			Statement stmt = this.conn.createStatement();		
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int id = rs.getInt("id");
				String localidad = rs.getString("localidad");
				
				this.localidadesList.add(new Localidades(id, localidad));
			}
		}catch (Exception e) {

			System.out.println("Se ha verificado un error al cargar las localidades.");
		}
	}

	public ArrayList<Localidades> getLocalidadesList() {
		return localidadesList;
	}	
	
	public Localidades getLocalidadById(int id) {
		
		for (Iterator iterator = localidadesList.iterator(); iterator.hasNext();) {
			
			Localidades localidades = (Localidades) iterator.next();
			
			if (localidades.getId() == id) {
				
				return localidades;
				
			} 
			
		}
		
		return null;
		
	}
		
}
