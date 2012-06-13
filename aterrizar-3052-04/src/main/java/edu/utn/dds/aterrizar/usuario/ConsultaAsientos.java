package edu.utn.dds.aterrizar.usuario;

import edu.utn.dds.aterrizar.vuelo.Vuelo;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;

public class ConsultaAsientos {

	private Vuelo vuelo;
	private FiltroAsiento filtro;
	
	public ConsultaAsientos(Vuelo vuelo, FiltroAsiento filtro) {
		this.vuelo = vuelo;
		this.filtro = filtro;
	}
	
}
