package edu.utn.dds.aterrizar.vuelo.ordenamiento;

import java.util.Comparator;

import net.sf.staccatocommons.collections.stream.Stream;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public class OrdenPorPrecioDescendente implements CriterioOrdenAsiento {

	@Override
	public Stream<Asiento> ordenar(Stream<Asiento> asientosFiltrados) {
		return asientosFiltrados.sortBy(new Comparator<Asiento>() {
			@Override
			public int compare(Asiento o1, Asiento o2) {
				return o1.getPrecio().compareTo(o2.getPrecio());
			}
		});
	}

}
