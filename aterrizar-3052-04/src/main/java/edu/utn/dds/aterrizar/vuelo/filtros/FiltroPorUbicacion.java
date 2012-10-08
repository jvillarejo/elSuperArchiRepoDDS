package edu.utn.dds.aterrizar.vuelo.filtros;

import static net.sf.staccatocommons.lambda.Lambda.$;
import static net.sf.staccatocommons.lambda.Lambda.lambda;
import net.sf.staccatocommons.collections.stream.Stream;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;

/**
 * Filtra asientos segun su ubicacion.
 * @param ubicacion
 * @param siguienteFiltro
 * @return un </code>Asiento</code> con sus fields seteados.
 */
public class FiltroPorUbicacion implements Filtro<Asiento> {
	
	private Ubicacion ubicacion;
	
	public FiltroPorUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public Stream<Asiento> filtrar(Stream<Asiento> asientos) {
		return asientos.filter(lambda($(Asiento.class).getUbicacion()).equal(this.ubicacion));
	}

}
