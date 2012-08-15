package edu.utn.dds.aterrizar.usuario;

import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;

public class ConsultaAsientos {

	private Busqueda vuelo;
	private FiltroAsiento filtro;
	
	public ConsultaAsientos(Busqueda vuelo, FiltroAsiento filtro) {
		this.vuelo = vuelo;
		this.filtro = filtro;
	}
	
	public Busqueda getVuelo() {
		return vuelo;
	}

	public FiltroAsiento getFiltro() {
		return filtro;
	}
	
}
