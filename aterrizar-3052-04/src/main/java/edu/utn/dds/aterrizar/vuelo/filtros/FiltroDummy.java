package edu.utn.dds.aterrizar.vuelo.filtros;

import net.sf.staccatocommons.collections.stream.Stream;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public class FiltroDummy implements FiltroAsiento {

	@Override
	public Stream<Asiento> filtrar(Stream<Asiento> asientos) {
		return asientos;
	}

}
