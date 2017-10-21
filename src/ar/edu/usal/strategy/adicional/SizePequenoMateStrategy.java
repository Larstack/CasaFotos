package ar.edu.usal.strategy.adicional;

public class SizePequenoMateStrategy extends CalculoAdicionalStrategy{

	public static final double adicional = 0.25;

	@Override
	public double calcularCosto() {
		
		double costo = (this.getCostoBase() + adicional);
		
		return costo + (costo * this.getIva());	}
}
