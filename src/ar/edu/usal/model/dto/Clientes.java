package ar.edu.usal.model.dto;

public class Clientes {
	
	private int id;
	private String nombreApellido;
	private String domicilio;
	private String mail;
	
	public Clientes() {
		super();
	}

	public Clientes(int id, String nombreApellido, String domicilio, String mail) {
		super();
		this.nombreApellido = nombreApellido;
		this.domicilio = domicilio;
		this.mail = mail;
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
		
}
