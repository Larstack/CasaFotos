package ar.edu.usal.strategy.adicional;

public class SizeMedianoMateStrategy extends CalculoAdicionalStrategy{

	public static final double adicional = 0.5;

	@Override
	public double calcularCosto() {
		
		double costo = (this.getCostoBase() + adicional);
		
		return costo + (costo * this.getIva());
	}
}
