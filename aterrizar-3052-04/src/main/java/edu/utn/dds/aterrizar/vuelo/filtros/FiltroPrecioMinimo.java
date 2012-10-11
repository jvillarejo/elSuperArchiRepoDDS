package edu.utn.dds.aterrizar.vuelo.filtros;

import static net.sf.staccatocommons.lambda.Lambda.$;
import static net.sf.staccatocommons.lambda.Lambda.lambda;
import static net.sf.staccatocommons.lang.Compare.greaterThan;
import net.sf.staccatocommons.collections.stream.Stream;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public class FiltroPrecioMinimo implements Filtro<Asiento> {
	private Double importe;

	public FiltroPrecioMinimo(Double importe) {
		this.importe = importe;
	}

	@Override
	public Stream<Asiento> filtrar(Stream<Asiento> asientos) {
		return asientos.filter(lambda($(Asiento.class).getPrecio()).then(greaterThan(this.importe)));
	}
}
