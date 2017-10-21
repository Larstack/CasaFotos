package ar.edu.usal.strategy.adicional;

public class SizeGrandeMateStrategy extends CalculoAdicionalStrategy{

	public static final double adicional = 1.5;

	@Override
	public double calcularCosto() {

		double costo = (this.getCostoBase() + adicional);
		
		return costo + (costo * this.getIva());
	}
}
