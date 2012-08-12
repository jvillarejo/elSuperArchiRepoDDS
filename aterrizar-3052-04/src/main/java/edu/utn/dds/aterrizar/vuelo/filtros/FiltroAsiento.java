package edu.utn.dds.aterrizar.vuelo.filtros;

import net.sf.staccatocommons.collections.stream.Stream;

import edu.utn.dds.aterrizar.vuelo.Asiento;

/**
 * Representa un filtro aplicable sobre una lista de asientos.
 * @author faloi
 */
public interface FiltroAsiento {
	public Stream<Asiento> filtrar(Stream<Asiento> asientos);
}
