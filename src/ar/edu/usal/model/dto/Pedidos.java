package ar.edu.usal.model.dto;

import ar.edu.usal.model.enums.PapelEnum;
import ar.edu.usal.model.enums.TamanioEnum;

public class Pedidos {

	private int numero;
	private Clientes cliente;
	private Sucursales sucursalRetiro;
	private Fotos foto;
	//El Pedido genera una Factura (clase implicita) con los datos de éste
	
	
	public Pedidos() {
		super();
	}


	public Pedidos(int numero, Clientes cliente, Sucursales sucursalRetiro) {
		super();
		this.numero = numero;
		this.cliente = cliente;
		this.sucursalRetiro = sucursalRetiro;
	}


	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Sucursales getSucursalRetiro() {
		return sucursalRetiro;
	}

	public void setSucursalRetiro(Sucursales sucursalRetiro) {
		this.sucursalRetiro = sucursalRetiro;
	}

	public Fotos getFoto() {
		return foto;
	}

	public void setFoto(TamanioEnum tamanio, PapelEnum papel, int cantidad) {
		this.foto = new Fotos(tamanio, papel, cantidad);
	}
	
}
