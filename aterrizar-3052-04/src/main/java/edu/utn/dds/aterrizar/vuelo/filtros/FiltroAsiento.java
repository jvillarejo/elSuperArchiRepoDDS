package edu.utn.dds.aterrizar.vuelo.filtros;

import java.util.List;

import edu.utn.dds.aterrizar.vuelo.Asiento;

public interface FiltroAsiento {
	public List<Asiento> filtrar(List<Asiento> asientos);
}
