package edu.utn.dds.aterrizar.usuario;

import java.util.List;

import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;
import edu.utn.dds.aterrizar.vuelo.ordenamiento.CriterioOrden;

public class ConsultaVuelos {

	private Busqueda busqueda;
	private List<Filtro<Asiento>> filtros;
	private CriterioOrden<Vuelo> criterioOrdenamiento;
	
	public ConsultaVuelos(Busqueda busqueda, List<Filtro<Asiento>> filtros, CriterioOrden<Vuelo> criterioOrdenamiento) {
		this.busqueda = busqueda;
		this.filtros = filtros;
		this.criterioOrdenamiento = criterioOrdenamiento;
	}
	
	public ConsultaVuelos(Busqueda busqueda) {
		this.busqueda= busqueda;
	}

	public Busqueda getBusqueda() {
		return busqueda;
	}

	public List<Filtro<Asiento>> getFiltros() {
		return this.filtros;
	}

	public CriterioOrden<Vuelo> getCriterioOrdenamiento() {
		return criterioOrdenamiento;
	}
}
