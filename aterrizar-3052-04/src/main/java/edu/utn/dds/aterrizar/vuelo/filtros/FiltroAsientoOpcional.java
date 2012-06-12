package edu.utn.dds.aterrizar.vuelo.filtros;

/**
 * Representa un filtro aplicable sobre una lista de asientos, que conoce al filtro que se va a aplicar despues.
 * @author faloi
 */

public abstract class FiltroAsientoOpcional implements FiltroAsiento {
	private FiltroAsiento nextFilter;

	public void setNextFilter(FiltroAsiento nextFilter) {
		this.nextFilter = nextFilter;
	}

	public FiltroAsiento getNextFilter() {
		return nextFilter;
	}	
}