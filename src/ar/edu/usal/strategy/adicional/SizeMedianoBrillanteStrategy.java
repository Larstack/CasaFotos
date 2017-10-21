package ar.edu.usal.strategy.adicional;

public class SizeMedianoBrillanteStrategy extends CalculoAdicionalStrategy{

	public static final double adicionalSize = 0.5;
	public static final double adicionalPapel = 1.2;

	@Override
	public double calcularCosto() {
		
		double costo = (this.getCostoBase() + adicionalSize + adicionalPapel);
		
		return costo + (costo * this.getIva());
	}
}
