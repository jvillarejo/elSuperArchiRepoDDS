package edu.utn.dds.aterrizar.vuelo.ordenamiento;

import static net.sf.staccatocommons.lambda.Lambda.*;

import net.sf.staccatocommons.collections.stream.Stream;
import edu.utn.dds.aterrizar.escalas.Vuelo;

public class OrdenPorTiempoDeVuelo implements CriterioOrden<Vuelo> {

	@Override
	public Stream<Vuelo> ordenar(Stream<Vuelo> elementosOrdenados) {
		return elementosOrdenados.sortOn(lambda($(Vuelo.class).getDuration()));
	}
	
}
