package edu.utn.dds.aterrizar.ui.mensajes;

public enum Accion {

	RESERVA("reserva", "reservado"),
	COMPRA("compra", "comprado");
	
	private String adjetivoAccion;

	private String accion;

	Accion(String accion, String adjetivoAccion) { 
		this.accion = accion;
		this.adjetivoAccion = adjetivoAccion;
	}
	
	public String getAdjetivoAccion() {
		return adjetivoAccion;
	}

	public String getAccion() {
		return accion;
	}
}
