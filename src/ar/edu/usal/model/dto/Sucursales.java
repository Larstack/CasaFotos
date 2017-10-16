package ar.edu.usal.model.dto;

public class Sucursales {
	
	private int numeroSucursal;
	private String direccion;
	private Localidades localidad;
	
	
	public Sucursales() {
		super();
	}

	public Sucursales(int numeroSucursal, String direccion, Localidades localidad) {
		super();
		this.numeroSucursal = numeroSucursal;
		this.direccion = direccion;
		this.localidad = localidad;
	}

	
	public int getNumeroSucursal() {
		return numeroSucursal;
	}

	public void setNumeroSucursal(int numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Localidades getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidades localidad) {
		this.localidad = localidad;
	}
	
}
