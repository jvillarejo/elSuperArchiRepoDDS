package edu.utn.dds.aterrizar.vuelo.filtros;

import net.sf.staccatocommons.collections.stream.Stream;

/**
 * Representa un filtro aplicable sobre una lista de asientos.
 * @author faloi
 */
public interface Filtro<T> {
	public Stream<T> filtrar(Stream<T> asientos);
}
