package edu.utn.dds.aterrizar.usuario;

import edu.utn.dds.aterrizar.vuelo.Vuelo;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;

public class ConsultaAsientos {

	private Vuelo vuelo;
	private FiltroAsiento[] filtros;
	
	public ConsultaAsientos(Vuelo vuelo, FiltroAsiento[] filtros) {
		this.vuelo = vuelo;
		this.filtros = filtros;
	}
	
	public Vuelo getVuelo() {
		return this.vuelo;
	}
	
	public FiltroAsiento[] getFiltros() {
		return this.filtros;
	}
}
