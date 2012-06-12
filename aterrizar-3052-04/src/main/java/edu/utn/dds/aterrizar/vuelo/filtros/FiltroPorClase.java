package edu.utn.dds.aterrizar.vuelo.filtros;

import static net.sf.staccatocommons.lambda.Lambda.$;
import static net.sf.staccatocommons.lambda.Lambda.lambda;

import java.util.List;

import net.sf.staccatocommons.collections.stream.Streams;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Clase;

public class FiltroPorClase implements FiltroAsiento {

	private Clase clase;
	
	public FiltroPorClase(Clase clase) {
		this.setClase(clase);
	}

	@Override
	public List<Asiento> filtrar(List<Asiento> asientos) {
		return Streams
	        .from(asientos)
	        .filter(lambda($(Asiento.class).getClase()).equal(this.clase))
	        .toList();
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public Clase getClase() {
		return clase;
	}

}
