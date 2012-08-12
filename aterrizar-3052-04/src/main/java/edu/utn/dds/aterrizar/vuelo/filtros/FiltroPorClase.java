package edu.utn.dds.aterrizar.vuelo.filtros;

import static net.sf.staccatocommons.lambda.Lambda.$;
import static net.sf.staccatocommons.lambda.Lambda.lambda;

import net.sf.staccatocommons.collections.stream.Stream;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Clase;

public class FiltroPorClase implements FiltroAsiento {

	private Clase clase;
	
	public FiltroPorClase(Clase clase) {
		this.clase = clase;
	}

	@Override
	public Stream<Asiento> filtrar(Stream<Asiento> asientos) {
        return asientos.filter(lambda($(Asiento.class).getClase()).equal(this.clase));
	}

}
