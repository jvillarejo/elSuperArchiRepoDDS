package edu.utn.dds.aterrizar.usuario;


public class Gratuita extends TipoDeSuscripcion {

	final Double RECARGO_SUSCRIPCION_GRATUITA = 20.0;
	
	@Override
	public Double getRecargo() {
		return RECARGO_SUSCRIPCION_GRATUITA;
	}

}
