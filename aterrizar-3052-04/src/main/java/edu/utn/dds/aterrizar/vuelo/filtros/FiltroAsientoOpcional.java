package edu.utn.dds.aterrizar.vuelo.filtros;

public abstract class FiltroAsientoOpcional implements FiltroAsiento {
	private FiltroAsiento nextFilter;

	public void setNextFilter(FiltroAsiento nextFilter) {
		this.nextFilter = nextFilter;
	}

	public FiltroAsiento getNextFilter() {
		return nextFilter;
	}	
}