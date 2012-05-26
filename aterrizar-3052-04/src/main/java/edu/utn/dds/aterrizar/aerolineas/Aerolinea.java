package edu.utn.dds.aterrizar.aerolineas;

import java.util.List;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Vuelo;

public interface Aerolinea {

	List<Asiento> buscarAsientos(Vuelo vuelo);
	
	void comprarAsiento(Asiento asientoDisponible, Usuario usuario);

	Double getPorcentajeDeVenta();
}
