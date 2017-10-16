package ar.edu.usal.model.interfaces;

import ar.edu.usal.model.enums.PapelEnum;
import ar.edu.usal.model.enums.TamanioEnum;

public interface ICalculoCosto {
	
	public static final double costoBase = 2.0;
	public static final double iva = 0.21;
	
	public enum AdicionalEnum {
		
		ADICIONAL_GRANDE(1.5),
		ADICIONAL_MEDIANO(0.5),
		ADICIONAL_PEQUENIO(0.25),
		ADICIONAL_MATE(0),
		ADICIONAL_BRILLANTE(1.2);
		
		private double adicional;
		
		AdicionalEnum(double adicional){
			
			this.adicional = adicional;
		}
		
		double getValueAdicional(){ return this.adicional;}
		
		double getAdicional(TamanioEnum tamanio, PapelEnum papel){
			
			double adicional = 0;
			
			if (tamanio == TamanioEnum.GRANDE)
				adicional = ADICIONAL_GRANDE.getValueAdicional();
			else if (tamanio == TamanioEnum.MEDIANO)
				adicional = ADICIONAL_MEDIANO.getValueAdicional();
			else
				adicional = ADICIONAL_PEQUENIO.getValueAdicional();
			
			if (papel == PapelEnum.MATE)
				adicional += ADICIONAL_MATE.getValueAdicional();
			else
				adicional += ADICIONAL_BRILLANTE.getValueAdicional();
			
			return adicional;
		}
		
	}

}
