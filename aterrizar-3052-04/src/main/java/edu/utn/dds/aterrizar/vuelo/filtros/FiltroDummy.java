package edu.utn.dds.aterrizar.vuelo.filtros;

import java.util.List;

import edu.utn.dds.aterrizar.vuelo.Asiento;

public class FiltroDummy implements FiltroAsiento {

	@Override
	public List<Asiento> filtrar(List<Asiento> asientos) {
		return asientos;
	}

}
