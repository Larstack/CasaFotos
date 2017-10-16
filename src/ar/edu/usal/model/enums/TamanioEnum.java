package ar.edu.usal.model.enums;


public enum TamanioEnum {
	
	GRANDE("18x21"),
	MEDIANO("13x18"),
	PEQUENIO("10x15");
	
	private String tamanio;
	
	public String getValueTamanio(){ return this.tamanio;}

	TamanioEnum(String tamanio){
		
		this.tamanio = tamanio;
	}
	
}

