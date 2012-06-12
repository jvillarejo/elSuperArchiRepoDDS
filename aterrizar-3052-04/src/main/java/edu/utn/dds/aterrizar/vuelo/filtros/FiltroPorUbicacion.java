package edu.utn.dds.aterrizar.vuelo.filtros;

import java.util.List;

import net.sf.staccatocommons.collections.stream.Streams;
import static net.sf.staccatocommons.lambda.Lambda.*;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;

public class FiltroPorUbicacion extends FiltroAsientoGenerico {
	
	private Ubicacion ubicacion;
	
	public FiltroPorUbicacion(Ubicacion ubicacion, FiltroAsiento siguienteFiltro) {
		this.ubicacion = ubicacion;
		this.setNextFilter(siguienteFiltro);
	}

	@Override
	public List<Asiento> filtrar(List<Asiento> asientos) {
		return Streams
			.from(asientos)
			.filter(lambda($(Asiento.class).getUbicacion()).equal(this.ubicacion))
			.toList();
	}

}
