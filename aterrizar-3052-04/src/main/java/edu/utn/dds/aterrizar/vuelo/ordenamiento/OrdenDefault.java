package edu.utn.dds.aterrizar.vuelo.ordenamiento;

import net.sf.staccatocommons.collections.stream.Stream;
import edu.utn.dds.aterrizar.escalas.Vuelo;

public class OrdenDefault implements CriterioOrden<Vuelo> {

	@Override
	public Stream<Vuelo> ordenar(Stream<Vuelo> elementosOrdenados) {
		return elementosOrdenados;
	}
	
}