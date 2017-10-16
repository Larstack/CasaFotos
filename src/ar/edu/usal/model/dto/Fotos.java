package ar.edu.usal.model.dto;

import ar.edu.usal.model.enums.PapelEnum;
import ar.edu.usal.model.enums.TamanioEnum;

public class Fotos {

	private TamanioEnum tamanio;
	private PapelEnum papel;
	private int cantidad;
	//Aca debe ir tambien el nombreArchivo (.jpg) a imprimir segun pedido
	
	public Fotos() {
		super();
	}

	public Fotos(TamanioEnum tamanio, PapelEnum papel, int cantidad) {
		super();
		this.tamanio = tamanio;
		this.papel = papel;
		this.cantidad = cantidad;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
