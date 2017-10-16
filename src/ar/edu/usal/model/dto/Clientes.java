package ar.edu.usal.model.dto;

public class Clientes {
	
	private String nombreApellido;
	private String domicilio;
	private String mail;
	private Localidades localidad;

	
	public Clientes() {
		super();
	}

	public Clientes(String nombreApellido, String domicilio, String mail, Localidades localidad) {
		super();
		this.nombreApellido = nombreApellido;
		this.domicilio = domicilio;
		this.mail = mail;
		this.localidad = localidad;
	}

	
	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Localidades getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidades localidad) {
		this.localidad = localidad;
	}
		
}
