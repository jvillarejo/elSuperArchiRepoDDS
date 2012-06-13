package edu.utn.dds.aterrizar.usuario;

import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroDummy;

public class Vip extends TipoDeSuscripcion {

	@Override
	public FiltroAsiento getFiltro() {
		return new FiltroDummy();
	}

}
