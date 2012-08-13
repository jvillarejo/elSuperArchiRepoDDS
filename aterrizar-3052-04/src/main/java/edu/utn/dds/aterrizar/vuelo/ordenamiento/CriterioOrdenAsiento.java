package edu.utn.dds.aterrizar.vuelo.ordenamiento;

import net.sf.staccatocommons.collections.stream.Stream;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public interface CriterioOrdenAsiento {
	Stream<Asiento> ordenar(Stream<Asiento> asientosFiltrados);
}
