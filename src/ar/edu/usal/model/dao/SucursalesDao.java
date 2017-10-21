package ar.edu.usal.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import ar.edu.usal.model.dto.Localidades;
import ar.edu.usal.model.dto.Sucursales;

public class SucursalesDao {
	
private static SucursalesDao sucursalesDaoInstance = null;
	
	private ArrayList<Sucursales> sucursalesList;
	private Connection conn;
	
	private SucursalesDao(){
		
		DbConnection dbConn = DbConnection.getInstance();
		this.conn = dbConn.getConnection();
		this.sucursalesList = new ArrayList<Sucursales>();
		this.loadsucursales();
	}

	public static SucursalesDao getInstance(){
		
		if(sucursalesDaoInstance==null){
			
			sucursalesDaoInstance = new SucursalesDao();
		}
		
		return sucursalesDaoInstance;
	}
	
	private void loadsucursales() {

		try {
			String sql = "SELECT id, direccion, localidad_id FROM Sucursales";

			Statement stmt = this.conn.createStatement();		
			ResultSet rs = stmt.executeQuery(sql);
			
			LocalidadesDao localidadesDao = LocalidadesDao.getInstance();
			
			while (rs.next()) {

				int id = rs.getInt("id");
				String direccion = rs.getString("direccion");
				int localidadId = rs.getInt("localidad_id");
				
				Localidades localidad = localidadesDao.getLocalidadById(localidadId);
				
				this.sucursalesList.add(new Sucursales(id,direccion,localidad));
			}
		}catch (Exception e) {

			System.out.println("Se ha verificado un error al cargar las sucursales.");
		}
	}

	public ArrayList<Sucursales> getsucursalesList() {
		return sucursalesList;
	}	
	
	public Sucursales getSucursalByNumeroSucursal(int numeroSucursal) {
		
		for (Iterator iterator = sucursalesList.iterator(); iterator.hasNext();) {
			
			Sucursales sucursales = (Sucursales) iterator.next();
			
			if (sucursales.getNumeroSucursal() == numeroSucursal) {
				
				return sucursales;
				
			} 
			
		}
		
		return null;
		
	}

	public ArrayList<Sucursales> getSucursalesList() {
		return sucursalesList;
	}

	public void setSucursalesList(ArrayList<Sucursales> sucursalesList) {
		this.sucursalesList = sucursalesList;
	}

}
