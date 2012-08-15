package edu.utn.dds.aterrizar.usuario;

import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;

public class ConsultaAsientos {

	private Busqueda vuelo;
	private FiltroAsiento[] filtros;
	
	public ConsultaAsientos(Busqueda vuelo, FiltroAsiento[] filtro) {
		this.vuelo = vuelo;
		this.filtros = filtros;
	}
	
	public Busqueda getVuelo() {
		return vuelo;
	}

	public FiltroAsiento[] getFiltros() {
		return this.filtros;
	}
}
