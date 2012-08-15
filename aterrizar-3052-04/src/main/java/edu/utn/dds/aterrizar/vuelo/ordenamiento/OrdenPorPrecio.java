package edu.utn.dds.aterrizar.vuelo.ordenamiento;

import static net.sf.staccatocommons.lambda.Lambda.*;

import net.sf.staccatocommons.collections.stream.Stream;
import edu.utn.dds.aterrizar.escalas.Vuelo;

public class OrdenPorPrecio implements CriterioOrden<Vuelo> {

	private Orden sentido;

	public OrdenPorPrecio(Orden sentido) {
		this.sentido = sentido;
	}

	@Override
	public Stream<Vuelo> ordenar(Stream<Vuelo> elementosOrdenados) {
		Stream<Vuelo> streamOrdenado = elementosOrdenados.sortOn(lambda($(Vuelo.class).getAsientoMasBarato().getPrecio()));

		if (this.sentido == Orden.DESCENDENTE)
			streamOrdenado.reverse();
		
		return streamOrdenado;
	}
	
}
