package edu.utn.dds.aterrizar.vuelo.filtros;

import java.util.List;

import net.sf.staccatocommons.collections.stream.Streams;
import static net.sf.staccatocommons.lambda.Lambda.lambda;
import static net.sf.staccatocommons.lambda.Lambda.$;

import edu.utn.dds.aterrizar.vuelo.Asiento;

public class FiltroSuperOfertas implements FiltroAsiento {

	@Override
	public List<Asiento> filtrar(List<Asiento> asientos) {
		
		return Streams
			.from(asientos)
			.filter(lambda($(Asiento.class).esSuperOferta()).equal(true))
			.toList();
		
	}

}
