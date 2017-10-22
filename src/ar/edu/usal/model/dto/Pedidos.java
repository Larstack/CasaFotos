package ar.edu.usal.model.dto;

import ar.edu.usal.model.enums.PapelEnum;
import ar.edu.usal.model.enums.TamanioEnum;
import ar.edu.usal.strategy.adicional.CalculoAdicionalStrategy;
import ar.edu.usal.strategy.adicional.SizeGrandeBrillanteStrategy;
import ar.edu.usal.strategy.adicional.SizeGrandeMateStrategy;
import ar.edu.usal.strategy.adicional.SizeMedianoBrillanteStrategy;
import ar.edu.usal.strategy.adicional.SizeMedianoMateStrategy;
import ar.edu.usal.strategy.adicional.SizePequenoBrillanteStrategy;
import ar.edu.usal.strategy.adicional.SizePequenoMateStrategy;

public class Pedidos {

	private int numero;
	private Clientes cliente;
	private Sucursales sucursalRetiro;
	private Fotos foto;
	private int cantidad;
	private CalculoAdicionalStrategy calculoAdicionalStrategy;
	private double importe;
	//El Pedido genera una Factura (clase implicita) con los datos de éste

	public Pedidos() {
		super();
	}

	public Pedidos(int numero, Clientes cliente, Sucursales sucursalRetiro, int cantidad, double importe,
				CalculoAdicionalStrategy calculoAdicionalStrategy) {
		super();
		this.numero = numero;
		this.cliente = cliente;
		this.sucursalRetiro = sucursalRetiro;
		this.cantidad = cantidad;
		this.calculoAdicionalStrategy = calculoAdicionalStrategy;
		this.importe = importe;
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

	public void setFoto(String tamanio, String papel, String path) {
		
		this.foto = new Fotos();
		
		this.foto.setPath(path);

		if (tamanio.equals(TamanioEnum.GRANDE.toString())) {
			
			this.foto.setTamanio(TamanioEnum.GRANDE);
			
			if(papel.equals(PapelEnum.BRILLANTE.toString())) {
				
				this.calculoAdicionalStrategy = new SizeGrandeBrillanteStrategy();
				this.foto.setPapel(PapelEnum.BRILLANTE);
			}
			else{
				
				this.calculoAdicionalStrategy = new SizeGrandeMateStrategy();
				this.foto.setPapel(PapelEnum.MATE);
			}
		}else if (tamanio.equals(TamanioEnum.MEDIANO.toString())) {
			
			this.foto.setTamanio(TamanioEnum.MEDIANO);
			
			if(papel.equals(PapelEnum.BRILLANTE.toString())) {
				
				this.foto.setPapel(PapelEnum.BRILLANTE);
				this.calculoAdicionalStrategy = new SizeMedianoBrillanteStrategy();
			}
			else{
				
				this.foto.setPapel(PapelEnum.MATE);
				this.calculoAdicionalStrategy = new SizeMedianoMateStrategy();
			}
		}else if (tamanio.equals(TamanioEnum.PEQUENIO.toString())) {
			
			this.foto.setTamanio(TamanioEnum.PEQUENIO);
			
			if(papel.equals(PapelEnum.BRILLANTE.toString())) {
				
				this.foto.setPapel(PapelEnum.BRILLANTE);
				this.calculoAdicionalStrategy = new SizePequenoBrillanteStrategy();
			}
			else{
				
				this.foto.setPapel(PapelEnum.MATE);
				this.calculoAdicionalStrategy = new SizePequenoMateStrategy();
			}
		}
		
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public CalculoAdicionalStrategy getCalculoAdicionalStrategy() {
		return calculoAdicionalStrategy;
	}

	public void setCalculoAdicionalStrategy(CalculoAdicionalStrategy calculoAdicionalStrategy) {
		this.calculoAdicionalStrategy = calculoAdicionalStrategy;
	}

	public void setImporte(double importe) {
		
		this.importe = importe;
	}
	
	public double getImporte() {
		return importe;
	}
	
	public void calculoCostoPedido(){
		
		this.importe = this.calculoAdicionalStrategy.calcularCosto() * this.cantidad;
	}
}
