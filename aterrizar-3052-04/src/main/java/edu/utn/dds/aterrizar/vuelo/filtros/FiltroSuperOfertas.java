package edu.utn.dds.aterrizar.vuelo.filtros;

import net.sf.staccatocommons.collections.stream.Stream;
import static net.sf.staccatocommons.lambda.Lambda.lambda;
import static net.sf.staccatocommons.lambda.Lambda.$;

import edu.utn.dds.aterrizar.vuelo.Asiento;

public class FiltroSuperOfertas implements Filtro<Asiento> {

	@Override
	public Stream<Asiento> filtrar(Stream<Asiento> asientos) {
		return asientos.filter(lambda($(Asiento.class).esSuperOferta()).equal(false));
	}

}
