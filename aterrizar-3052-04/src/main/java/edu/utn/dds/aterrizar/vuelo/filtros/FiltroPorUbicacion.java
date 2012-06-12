package edu.utn.dds.aterrizar.vuelo.filtros;

import java.util.List;

import net.sf.staccatocommons.collections.stream.Streams;
import static net.sf.staccatocommons.lambda.Lambda.*;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;

/**
 * Filtra asientos segun su ubicacion.
 * @param ubicacion
 * @param siguienteFiltro
 * @return un </code>Asiento</code> con sus fields seteados.
 */
public class FiltroPorUbicacion extends FiltroAsientoOpcional {
	
	private Ubicacion ubicacion;
	
	public FiltroPorUbicacion(Ubicacion ubicacion, FiltroAsiento siguienteFiltro) {
		this.ubicacion = ubicacion;
		this.setNextFilter(siguienteFiltro);
	}

	@Override
	public List<Asiento> filtrar(List<Asiento> asientos) {
		List<Asiento> nuevosAsientos = Streams
			.from(asientos)
			.filter(lambda($(Asiento.class).getUbicacion()).equal(this.ubicacion))
			.toList();
		
		return this.getNextFilter().filtrar(nuevosAsientos);
	}

}
