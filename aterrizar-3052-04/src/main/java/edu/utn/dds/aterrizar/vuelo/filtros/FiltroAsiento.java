package edu.utn.dds.aterrizar.vuelo.filtros;

import java.util.List;

import edu.utn.dds.aterrizar.vuelo.Asiento;

/**
 * Representa un filtro aplicable sobre una lista de asientos.
 * @author faloi
 */
public interface FiltroAsiento {
	public List<Asiento> filtrar(List<Asiento> asientos);
}
