package edu.utn.dds.aterrizar.vuelo.filtros;

import static net.sf.staccatocommons.lambda.Lambda.$;
import static net.sf.staccatocommons.lambda.Lambda.lambda;

import java.util.List;

import net.sf.staccatocommons.collections.stream.Streams;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Clase;

public class FiltroPorClase extends FiltroAsientoGenerico {

	private Clase clase;
	
	public FiltroPorClase(Clase clase, FiltroAsiento siguienteFiltro) {
		this.clase = clase;
		this.setNextFilter(siguienteFiltro);
	}

	@Override
	public List<Asiento> filtrar(List<Asiento> asientos) {
		List<Asiento> nuevosAsientos = Streams
	        .from(asientos)
	        .filter(lambda($(Asiento.class).getClase()).equal(this.clase))
	        .toList();
		
		return this.getNextFilter().filtrar(nuevosAsientos);		
	}

}
