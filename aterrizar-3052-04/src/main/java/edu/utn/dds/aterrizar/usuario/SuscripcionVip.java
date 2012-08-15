package edu.utn.dds.aterrizar.usuario;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroDummy;

public class SuscripcionVip extends TipoDeSuscripcion {

	@Override
	public Filtro<Asiento> getFiltro() {
		return new FiltroDummy();
	}

}
