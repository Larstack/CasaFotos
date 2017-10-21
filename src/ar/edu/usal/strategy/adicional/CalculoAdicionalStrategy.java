package ar.edu.usal.strategy.adicional;

public abstract class CalculoAdicionalStrategy implements ICalculoAdicionalStrategy{

	public static final double costoBase = 2.0;
	public static final double iva = 0.21;
	
	@Override
	public abstract double calcularCosto();

	public double getCostoBase(){
		
		return costoBase;
	}
	
	public double getIva(){
		
		return iva;
	}
}
