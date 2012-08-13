package edu.utn.dds.aterrizar.vuelo.ordenamiento;

import java.util.Comparator;

import net.sf.staccatocommons.collections.stream.Stream;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public class OrdenPorPrecio implements CriterioOrdenAsiento {

	private Orden sentido;

	public OrdenPorPrecio(Orden sentido) {
		this.sentido = sentido;
	}
	
	@Override
	public Stream<Asiento> ordenar(Stream<Asiento> asientosFiltrados) {
		Comparator<Asiento> comparator = new Comparator<Asiento>() {
			@Override
			public int compare(Asiento o1, Asiento o2) {
				return o1.getPrecio().compareTo(o2.getPrecio());
			}
		};
		
		Stream<Asiento> streamOrdenado = asientosFiltrados.sortBy(comparator);
		return this.sentido == Orden.DESCENDENTE? streamOrdenado	: streamOrdenado.reverse();
	}
	
}
