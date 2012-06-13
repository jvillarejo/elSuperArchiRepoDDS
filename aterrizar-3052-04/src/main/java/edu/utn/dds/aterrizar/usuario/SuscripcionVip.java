package edu.utn.dds.aterrizar.usuario;

import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroDummy;

public class SuscripcionVip extends TipoDeSuscripcion {

	@Override
	public FiltroAsiento getFiltro() {
		return new FiltroDummy();
	}

}
