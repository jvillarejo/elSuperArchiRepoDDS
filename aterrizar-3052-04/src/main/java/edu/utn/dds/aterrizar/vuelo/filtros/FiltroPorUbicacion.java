package edu.utn.dds.aterrizar.vuelo.filtros;

import java.util.List;

import net.sf.staccatocommons.collections.stream.Streams;
import static net.sf.staccatocommons.lambda.Lambda.*;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;

public class FiltroPorUbicacion implements FiltroAsiento {
	
	private Ubicacion ubicacion;
	
	public FiltroPorUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public List<Asiento> filtrar(List<Asiento> asientos) {
		// TODO Auto-generated method stub
		return Streams
			.from(asientos)
			.filter(lambda($(Asiento.class).getUbicacion()).equal(this.ubicacion))
			.toList();
	}

}
