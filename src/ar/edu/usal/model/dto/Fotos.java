package ar.edu.usal.model.dto;

import ar.edu.usal.model.enums.PapelEnum;
import ar.edu.usal.model.enums.TamanioEnum;

public class Fotos {

	private TamanioEnum tamanio;
	private PapelEnum papel;
//	private int cantidad;
	private String path;
	private int id;
	
	public Fotos() {
		super();
	}

	public Fotos(TamanioEnum tamanio, PapelEnum papel, String path, int id) {
		super();
		this.tamanio = tamanio;
		this.papel = papel;
//		this.cantidad = cantidad;
		this.setPath(path);
		this.setId(id);
	}

	
	public TamanioEnum getTamanio() {
		return tamanio;
	}

	public void setTamanio(TamanioEnum tamanio) {
		this.tamanio = tamanio;
	}

	public PapelEnum getPapel() {
		return papel;
	}

	public void setPapel(PapelEnum papel) {
		this.papel = papel;
	}

//	public int getCantidad() {
//		return cantidad;
//	}
//
//	public void setCantidad(int cantidad) {
//		this.cantidad = cantidad;
//	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
