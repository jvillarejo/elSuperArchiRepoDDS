package edu.utn.dds.aterrizar.usuario;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;

public class ConsultaAsientos {

	private Busqueda vuelo;
	private Filtro<Asiento>[] filtros;
	
	public ConsultaAsientos(Busqueda vuelo, Filtro<Asiento>[] filtros) {
		this.vuelo = vuelo;
		this.filtros = filtros;
	}
	
	public Busqueda getVuelo() {
		return vuelo;
	}

	public Filtro<Asiento>[] getFiltros() {
		return this.filtros;
	}
}
