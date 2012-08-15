package edu.utn.dds.aterrizar.vuelo.ordenamiento;

import net.sf.staccatocommons.collections.stream.Stream;

public interface CriterioOrden<T> {
	Stream<T> ordenar(Stream<T> elementosOrdenados);
}
